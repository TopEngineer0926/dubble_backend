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
import java.lang.Object;
import com.mogree.spring.exception.APIItemNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mogree.spring.response.IResponse;

/**
 * A delegate to be called by the {@link ProductApiController}}.
 * Should be implemented as a controller but without the {@link org.springframework.stereotype.Controller} annotation.
 * Instead, use spring to autowire this class into the {@link ProductApiController}.
 */
@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public interface ProductApiDelegate {

    /**
     * @see ProductApi#createProduct
     */
    public Object createProduct(ParamProductModelBody paramProductModelBody, ParamCreateProduct paramCreateProduct);

    /**
     * @see ProductApi#deleteProduct
     */
    public Object deleteProduct(ParamDeleteProduct paramDeleteProduct);

    /**
     * @see ProductApi#getProductDetail
     */
    public Object getProductDetail(ParamGetProductDetail paramGetProductDetail);

    /**
     * @see ProductApi#getProductList
     */
    public Object getProductList(ParamPaging paramPaging, ParamGetProductList paramGetProductList);

    /**
     * @see ProductApi#getProductPage
     */
    public Object getProductPage(ParamGetProductPage paramGetProductPage);

    /**
     * @see ProductApi#sendProductPublishedNotification
     */
    public Object sendProductPublishedNotification(ParamProductNotification paramProductNotification, ParamSendProductPublishedNotification paramSendProductPublishedNotification);

    /**
     * @see ProductApi#updateProduct
     */
    public Object updateProduct(ParamProductModelBody paramProductModelBody, ParamUpdateProduct paramUpdateProduct);

    @Component
    class ProductApiDelegateDefaultImpl implements ProductApiDelegate {
        @Override
        public Object createProduct(ParamProductModelBody paramProductModelBody, ParamCreateProduct paramCreateProduct){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object deleteProduct(ParamDeleteProduct paramDeleteProduct){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getProductDetail(ParamGetProductDetail paramGetProductDetail){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getProductList(ParamPaging paramPaging, ParamGetProductList paramGetProductList){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getProductPage(ParamGetProductPage paramGetProductPage){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object sendProductPublishedNotification(ParamProductNotification paramProductNotification, ParamSendProductPublishedNotification paramSendProductPublishedNotification){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object updateProduct(ParamProductModelBody paramProductModelBody, ParamUpdateProduct paramUpdateProduct){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
    }
}
