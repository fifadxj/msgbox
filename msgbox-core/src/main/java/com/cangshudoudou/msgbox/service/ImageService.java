package com.cangshudoudou.msgbox.service;

import java.util.List;

import com.cangshudoudou.msgbox.vo.Category;
import com.cangshudoudou.msgbox.vo.Image;

public interface ImageService {
    Category createImage(Image image);
    void deleteImage(Long id);
    Image getImage(Long id);
    List<Image> getImagesByMessageId(Long messageId);
}
