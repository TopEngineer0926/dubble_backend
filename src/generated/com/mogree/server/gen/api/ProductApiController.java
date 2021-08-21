package com.mogree.server.gen.api;

import com.mogree.spring.response.DetailResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.model.ProductModel;
import com.mogree.spring.response.StatusResponse;
import com.mogree.spring.response.ListResponse;
import org.springframework.core.io.Resource;
import com.mogree.server.gen.param.ParamProductModelBody;
import com.mogree.server.gen.param.ParamCreateProduct;
import com.mogree.server.gen.param.ParamDeleteProduct;
import com.mogree.server.gen.param.ParamGetProductDetail;
import com.mogree.server.gen.param.ParamGetProductList;
import com.mogree.server.gen.param.ParamPaging;
import com.mogree.server.gen.param.ParamGetProductPage;
import com.mogree.server.gen.param.ParamProductNotification;
import com.mogree.server.gen.param.ParamSendProductPublishedNotification;
import com.mogree.server.gen.param.ParamUpdateProduct;

import java.util.List;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.mogree.spring.validator.IValidator;

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

@RestController
public class ProductApiController implements ProductApi {

    @Autowired(required=false)
    private List<IValidator<? extends Object>> validators;

    private ProductApiDelegate delegate;

    @Autowired
    public ProductApiController(ProductApiDelegate delegate) {
        this.delegate = delegate;
    }


    public ResponseEntity<DetailResponse<ProductModel>> createProduct(@ApiParam(value = ""  ) @RequestBody ProductModel productModelBody) {

        ParamProductModelBody paramProductModelBody = new ParamProductModelBody(productModelBody);
        ParamCreateProduct paramCreateProduct = new ParamCreateProduct();

        return new Executer(validators).validate(paramProductModelBody).usecase(() -> delegate.createProduct(paramProductModelBody, paramCreateProduct)).run();

    }


    public ResponseEntity<StatusResponse> deleteProduct(@ApiParam(value = "",required=true, example = "0") @PathVariable("productId") Long productId) {

        ParamDeleteProduct paramDeleteProduct = new ParamDeleteProduct(productId);

        return new Executer(validators).validate().usecase(() -> delegate.deleteProduct(paramDeleteProduct)).run();

    }


    public ResponseEntity<DetailResponse<ProductModel>> getProductDetail(@ApiParam(value = "",required=true, example = "0") @PathVariable("productId") Long productId) {

        ParamGetProductDetail paramGetProductDetail = new ParamGetProductDetail(productId);

        return new Executer(validators).validate().usecase(() -> delegate.getProductDetail(paramGetProductDetail)).run();

    }


    public ResponseEntity<ListResponse<ProductModel>> getProductList(@ApiParam(value = "Paging offset", required = true, defaultValue = "0", example = "0")  @RequestParam(value = "offset", required = true, defaultValue="0") Integer offset, @ApiParam(value = "Paging limit", required = true, defaultValue = "-1", example = "-1")  @RequestParam(value = "limit", required = true, defaultValue="-1") Integer limit, @ApiParam(value = "Sort the list", allowableValues = "createdAt")  @RequestParam(value = "sort_column", required = false) String sortColumn, @ApiParam(value = "Sort Order", allowableValues = "asc, desc")  @RequestParam(value = "sort_order", required = false) String sortOrder) {

        ParamPaging paramPaging = new ParamPaging(offset, limit, sortColumn, sortOrder);
        ParamGetProductList paramGetProductList = new ParamGetProductList();

        return new Executer(validators).validate(paramPaging).usecase(() -> delegate.getProductList(paramPaging, paramGetProductList)).run();

    }


    public ResponseEntity<Resource> getProductPage(@ApiParam(value = "",required=true, example = "0") @PathVariable("productShareCode") String productShareCode) {

        ParamGetProductPage paramGetProductPage = new ParamGetProductPage(productShareCode);

        return new Executer(validators).validate().usecase(() -> delegate.getProductPage(paramGetProductPage)).run();

    }


    public ResponseEntity<StatusResponse> sendProductPublishedNotification(@ApiParam(value = "",required=true, example = "0") @PathVariable("productId") Long productId, @ApiParam(value = "e.g. sms, email",required=true, example = "0") @PathVariable("notificationType") String notificationType) {

        ParamProductNotification paramProductNotification = new ParamProductNotification(productId, notificationType);
        ParamSendProductPublishedNotification paramSendProductPublishedNotification = new ParamSendProductPublishedNotification();

        return new Executer(validators).validate(paramProductNotification).usecase(() -> delegate.sendProductPublishedNotification(paramProductNotification, paramSendProductPublishedNotification)).run();

    }


    public ResponseEntity<DetailResponse<ProductModel>> updateProduct(@ApiParam(value = ""  ) @RequestBody ProductModel productModelBody) {

        ParamProductModelBody paramProductModelBody = new ParamProductModelBody(productModelBody);
        ParamUpdateProduct paramUpdateProduct = new ParamUpdateProduct();

        return new Executer(validators).validate(paramProductModelBody).usecase(() -> delegate.updateProduct(paramProductModelBody, paramUpdateProduct)).run();

    }


}

