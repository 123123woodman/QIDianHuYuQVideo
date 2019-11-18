package qd.com.library.bean;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "WDQCATEGORY_BEAN".
 */
@Entity
public class WDQCategoryBean {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String categoryName;

    @Generated
    public WDQCategoryBean() {
    }

    public WDQCategoryBean(Long id) {
        this.id = id;
    }

    @Generated
    public WDQCategoryBean(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getCategoryName() {
        return categoryName;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCategoryName(@NotNull String categoryName) {
        this.categoryName = categoryName;
    }

}
