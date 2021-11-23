package my.course.cafecase.entity;

import javax.persistence.*;


@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFood;
    private String nameFood;
    private Double costFood;
    private String tagFood;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private boolean isHave;

    public User getAuthor() {
        return author;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "undefined";
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getIdFood() {
        return idFood;
    }

    public void setIdFood(Long idFood) {
        this.idFood = idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public Double getCostFood() {
        return costFood;
    }

    public void setCostFood(Double costFood) {
        this.costFood = costFood;
    }

    public String getTagFood() {
        return tagFood;
    }

    public void setTagFood(String tagFood) {
        this.tagFood = tagFood;
    }

    public boolean isHave() {
        return isHave;
    }

    public void setHave(boolean have) {
        isHave = have;
    }

    public Menu() {
    }

    public Menu(String nameFood, Double costFood, String tagFood, User author, boolean isHave) {
        this.nameFood = nameFood;
        this.costFood = costFood;
        this.tagFood = tagFood;
        this.author = author;
        this.isHave = isHave;
    }

    public Menu(String nameFood, double costFood, String tagFood, boolean isHave) {
        this.nameFood = nameFood;
        this.costFood = costFood;
        this.tagFood = tagFood;
        this.isHave = isHave;
    }
}
