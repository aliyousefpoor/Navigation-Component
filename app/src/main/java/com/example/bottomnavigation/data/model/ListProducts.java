package com.example.bottomnavigation.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListProducts {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_english")
    @Expose
    private String nameEnglish;
    @SerializedName("product_type")
    @Expose
    private Integer productType;
    @SerializedName("producer_name")
    @Expose
    private String producerName;
    @SerializedName("payment_type")
    @Expose
    private List<Object> paymentType = null;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("price_show")
    @Expose
    private Object priceShow;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;
    @SerializedName("feature_avatar")
    @Expose
    private FeatureAvatar featureAvatar;
    @SerializedName("rank")
    @Expose
    private Double rank;
    @SerializedName("totalInstalled")
    @Expose
    private Integer totalInstalled;
    @SerializedName("short_description")
    @Expose
    private String shortDescription;
    @SerializedName("is_purchased")
    @Expose
    private String isPurchased;
    @SerializedName("comments")
    @Expose
    private Integer comments;
    @SerializedName("is_bookmarked")
    @Expose
    private Integer isBookmarked;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("tags")
    @Expose
    private List<Tags> tags = null;
    @SerializedName("category_model")
    @Expose
    private List<Category> categoryModel = null;
    @SerializedName("comments_summery")
    @Expose
    private List<CommentsSummery> commentsSummery = null;
    @SerializedName("price_unit")
    @Expose
    private String priceUnit;
    @SerializedName("total_view")
    @Expose
    private Integer totalView;
    @SerializedName("custom_json")
    @Expose
    private String customJson;
    @SerializedName("polls")
    @Expose
    private List<Object> polls=null;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("invest_goal")
    @Expose
    private Integer investGoal;
    @SerializedName("product_staff")
    @Expose
    private List<Object> productStaff;
    @SerializedName("support")
    @Expose
    private Support support;
    @SerializedName("is_special")
    @Expose
    private String isSpecial;
    @SerializedName("additional_attributes")
    @Expose
    private List<Object> additionalAttributes;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("customjson")
    @Expose
    private String customjson;
    @SerializedName("approved_age")
    @Expose
    private Object approvedAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEnglish() {
        return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
        this.nameEnglish = nameEnglish;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public List<Object> getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(List<Object> paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Object getPriceShow() {
        return priceShow;
    }

    public void setPriceShow(Object priceShow) {
        this.priceShow = priceShow;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public FeatureAvatar getFeatureAvatar() {
        return featureAvatar;
    }

    public void setFeatureAvatar(FeatureAvatar featureAvatar) {
        this.featureAvatar = featureAvatar;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Integer getTotalInstalled() {
        return totalInstalled;
    }

    public void setTotalInstalled(Integer totalInstalled) {
        this.totalInstalled = totalInstalled;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(String isPurchased) {
        this.isPurchased = isPurchased;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(Integer isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Category> getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(List<Category> categoryModel) {
        this.categoryModel = categoryModel;
    }

    public List<CommentsSummery> getCommentsSummery() {
        return commentsSummery;
    }

    public void setCommentsSummery(List<CommentsSummery> commentsSummery) {
        this.commentsSummery = commentsSummery;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getTotalView() {
        return totalView;
    }

    public void setTotalView(Integer totalView) {
        this.totalView = totalView;
    }

    public String getCustomJson() {
        return customJson;
    }

    public void setCustomJson(String customJson) {
        this.customJson = customJson;
    }

    public List<Object> getPolls() {
        return polls;
    }

    public void setPolls(List<Object> polls) {
        this.polls = polls;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getInvestGoal() {
        return investGoal;
    }

    public void setInvestGoal(Integer investGoal) {
        this.investGoal = investGoal;
    }

    public List<Object> getProductStaff() {
        return productStaff;
    }

    public void setProductStaff(List<Object> productStaff) {
        this.productStaff = productStaff;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

    public String getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(String isSpecial) {
        this.isSpecial = isSpecial;
    }

    public List<Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(List<Object> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getCustomjson() {
        return customjson;
    }

    public void setCustomjson(String customjson) {
        this.customjson = customjson;
    }

    public Object getApprovedAge() {
        return approvedAge;
    }

    public void setApprovedAge(Object approvedAge) {
        this.approvedAge = approvedAge;
    }

}
