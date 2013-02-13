package info.cangshudoudou.msgbox.vo;

public class MessageFilterCondition {
    private Long categoryId;
    private Boolean disabled = false;
    private Boolean top;
    public Boolean getTop() {
        return top;
    }
    public void setTop(Boolean top) {
        this.top = top;
    }
    public Boolean getDisabled() {
        return disabled;
    }
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
}
