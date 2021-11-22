package my.course.cafecase.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFood;
    private String nameFood;
    private Float costFood;
    private String tagFood;
    private boolean isHave;

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

    public Float getCostFood() {
        return costFood;
    }

    public void setCostFood(Float costFood) {
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
}
