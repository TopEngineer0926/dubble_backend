package com.mogree.server.gen.api;

import com.mogree.spring.response.StatusResponse;
import com.mogree.spring.Executer;
import com.mogree.server.gen.model.MediaModel;
import com.mogree.spring.response.ListResponse;
import com.mogree.spring.response.DetailResponse;
import org.springframework.core.io.Resource;
import com.mogree.server.gen.param.ParamDeleteImage;
import com.mogree.server.gen.param.ParamGetMedia;
import com.mogree.server.gen.param.ParamPaging;
import com.mogree.server.gen.param.ParamUpdateMedia;
import com.mogree.server.gen.param.ParamUploadImage;

import java.util.List;
import java.lang.Object;
import com.mogree.spring.exception.APIItemNotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mogree.spring.response.IResponse;

/**
 * A delegate to be called by the {@link MediaApiController}}.
 * Should be implemented as a controller but without the {@link org.springframework.stereotype.Controller} annotation.
 * Instead, use spring to autowire this class into the {@link MediaApiController}.
 */
@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

public interface MediaApiDelegate {

    /**
     * @see MediaApi#deleteImage
     */
    public Object deleteImage(ParamDeleteImage paramDeleteImage);

    /**
     * @see MediaApi#getMedia
     */
    public Object getMedia(ParamPaging paramPaging, ParamGetMedia paramGetMedia);

    /**
     * @see MediaApi#updateMedia
     */
    public Object updateMedia(ParamUpdateMedia paramUpdateMedia);

    /**
     * @see MediaApi#uploadImage
     */
    public Object uploadImage(ParamUploadImage paramUploadImage);

    @Component
    class MediaApiDelegateDefaultImpl implements MediaApiDelegate {
        @Override
        public Object deleteImage(ParamDeleteImage paramDeleteImage){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object getMedia(ParamPaging paramPaging, ParamGetMedia paramGetMedia){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object updateMedia(ParamUpdateMedia paramUpdateMedia){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
        @Override
        public Object uploadImage(ParamUploadImage paramUploadImage){
            if (true){
                throw new APIItemNotFoundException("This API is not implemented yet. Plz be patient or give a present to the backend developer.");
            }
            return null;
        }
    }
}
