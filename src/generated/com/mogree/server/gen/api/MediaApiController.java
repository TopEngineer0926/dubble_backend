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
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import com.mogree.spring.validator.IValidator;

@javax.annotation.Generated(value = "class com.mogree.MogreeCodeGen", date = "2021-05-11T13:27:53.093+02:00")

@RestController
public class MediaApiController implements MediaApi {

    @Autowired(required=false)
    private List<IValidator<? extends Object>> validators;

    private MediaApiDelegate delegate;

    @Autowired
    public MediaApiController(MediaApiDelegate delegate) {
        this.delegate = delegate;
    }


    public ResponseEntity<StatusResponse> deleteImage(@ApiParam(value = "",required=true, example = "0") @PathVariable("mediaId") Integer mediaId) {

        ParamDeleteImage paramDeleteImage = new ParamDeleteImage(mediaId);

        return new Executer(validators).validate().usecase(() -> delegate.deleteImage(paramDeleteImage)).run();

    }


    public ResponseEntity<ListResponse<MediaModel>> getMedia(@ApiParam(value = "",required=true, example = "0") @PathVariable("modelName") String modelName, @ApiParam(value = "",required=true, example = "0") @PathVariable("modelId") Integer modelId, @ApiParam(value = "Paging offset", required = true, defaultValue = "0", example = "0")  @RequestParam(value = "offset", required = true, defaultValue="0") Integer offset, @ApiParam(value = "Paging limit", required = true, defaultValue = "-1", example = "-1")  @RequestParam(value = "limit", required = true, defaultValue="-1") Integer limit, @ApiParam(value = "Sort the list", allowableValues = "createdAt, order")  @RequestParam(value = "sort_column", required = false) String sortColumn, @ApiParam(value = "Sort Order", allowableValues = "asc, desc")  @RequestParam(value = "sort_order", required = false) String sortOrder) {

        ParamPaging paramPaging = new ParamPaging(offset, limit, sortColumn, sortOrder);
        ParamGetMedia paramGetMedia = new ParamGetMedia(modelName, modelId);

        return new Executer(validators).validate(paramPaging).usecase(() -> delegate.getMedia(paramPaging, paramGetMedia)).run();

    }


    public ResponseEntity<DetailResponse<MediaModel>> updateMedia(@ApiParam(value = "ID of the Media",required=true, example = "0") @PathVariable("mediaId") Integer mediaId, @ApiParam(value = "Title of the Media")  @RequestParam(value = "title", required = false) String title, @ApiParam(value = "Display order of the Media")  @RequestParam(value = "order", required = false) Integer order) {

        ParamUpdateMedia paramUpdateMedia = new ParamUpdateMedia(mediaId, title, order);

        return new Executer(validators).validate().usecase(() -> delegate.updateMedia(paramUpdateMedia)).run();

    }


    public ResponseEntity<DetailResponse<MediaModel>> uploadImage(@ApiParam(value = "e.g. User, Product, Contact",required=true, example = "0") @PathVariable("modelName") String modelName, @ApiParam(value = "Id of the e.g. User, Product, Contact",required=true, example = "0") @PathVariable("modelId") Integer modelId, @ApiParam(value = "file detail") @RequestPart("file") MultipartFile file, @ApiParam(value = "Title of the Video")  @RequestParam(value = "title", required = false) String title, @ApiParam(value = "Order used to display on Client")  @RequestParam(value = "order", required = false) Integer order) {

        ParamUploadImage paramUploadImage = new ParamUploadImage(modelName, modelId, file, title, order);

        return new Executer(validators).validate().usecase(() -> delegate.uploadImage(paramUploadImage)).run();

    }


}

