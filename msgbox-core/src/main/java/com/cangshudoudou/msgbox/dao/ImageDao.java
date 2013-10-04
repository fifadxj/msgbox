package com.cangshudoudou.msgbox.dao;

import java.util.List;

import com.cangshudoudou.msgbox.vo.Category;
import com.cangshudoudou.msgbox.vo.Image;

public interface ImageDao {
    Category createImage(Image image);
    void deleteImage(Long id);
    void updateImage(Image image);
    Image getImage(Long id);
    List<Image> getImagesByMessageId(Long messageId);
    void deleteImagesByMessageId(Long messageId);
}
