package com.demo.fashion.service.impl;

import com.demo.fashion.config.ApplicationProperties;
import com.demo.fashion.domain.User;
import com.demo.fashion.repository.UserRepository;
import com.demo.fashion.security.SecurityUtils;
import com.demo.fashion.service.ProductService;
import com.demo.fashion.domain.Product;
import com.demo.fashion.repository.ProductRepository;
import com.demo.fashion.service.util.FileUploadUtils;
import com.demo.fashion.web.rest.vm.FileDTO;
import com.demo.fashion.web.rest.vm.ProductVM;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Save a product.
     *
     * @param product the entity to save
     * @return the persisted entity
     */
    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        Product result = productRepository.save(product);
        return result;
    }

    /**
     *  Get all the products.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        Page<Product> result = productRepository.findAll(pageable);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        log.debug("Request to get all Products");
        List<Product> result = productRepository.findAll();
        return result;
    }

    /**
     *  Get one product by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        Product product = productRepository.findOne(id);
        return product;
    }

    /**
     *  Delete the  product by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.delete(id);
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        log.debug("Rest to find product has name contain: {}", name);
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Product saveProduct(ProductVM productVM) throws IOException {
        log.debug("Rest to save product: {}", productVM);
        //get User
        String username = SecurityUtils.getCurrentUserLogin();
        User user = userRepository.findOneByLogin(username).get();
        if (productVM.getPhoto() == null && productVM.getImage() != null) {
            //save file
            FileDTO file = productVM.getImage();
            String filePath = FileUploadUtils.saveFile(applicationProperties.getFolderUpload(), file.getFileData(), file.getFileName());
            productVM.setPhoto(filePath);
        }
        //save product
        Product product = toEntity(productVM, user);
        return productRepository.save(product);
    }

    /**
     *
     * @param productVM params
     * @param user user login
     * @return entity
     */
    private Product toEntity(ProductVM productVM, User user){
        if(productVM.getId() != null){
            Product product = productRepository.findOne(productVM.getId());
            product
                .name(productVM.getName())
                .photo(productVM.getPhoto())
                .manufacturer(productVM.getManufacturer())
                .quantity(productVM.getQuantity())
                .price(productVM.getPrice())
                .currentSale(productVM.getCurrentSale())
                .fullTextPost(productVM.getFullTextPost())
                .category(productVM.getCategory());
            return product;
        }
        return new Product()
            .id(productVM.getId())
            .name(productVM.getName())
            .photo(productVM.getPhoto())
            .manufacturer(productVM.getManufacturer())
            .quantity(productVM.getQuantity())
            .price(productVM.getPrice())
            .currentSale(productVM.getCurrentSale())
            .fullTextPost(productVM.getFullTextPost())
            .timeCreated(ZonedDateTime.now())
            .category(productVM.getCategory())
            .user(user);
    }


}
