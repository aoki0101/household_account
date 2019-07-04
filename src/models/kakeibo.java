package models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "kakeibo")
@NamedQueries({
    @NamedQuery(
            name = "getMyAllKakeibo",
            query = "SELECT k FROM kakeibo AS k WHERE   k.employee = :employee AND k.kakeibo_date >=:firstday AND k.kakeibo_date <= :lastday ORDER BY k.id DESC"
            ),
    @NamedQuery(
            name = "getTodayCost",
            query = "SELECT k FROM kakeibo AS k WHERE k.employee = :employee AND k.kakeibo_date = :today"),
    @NamedQuery(
            name = "getKakeiboCount",
            query = "SELECT COUNT(k) FROM kakeibo AS k"
            ),
    //SUM 集計
})

@Entity
public class kakeibo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name ="kakeibo_date", nullable = false)
    private Date kakeibo_date;

    @Lob
    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;


    //費用の種類<収入カテゴリー>
    @Column(name = "category", nullable  =false)
    private Integer category;
    //商品
    @Column(name = "product", nullable = false)
    private String product;
    //収入


    @Column(name = "cost" , nullable = false)
    private Integer cost;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product){
        this.product = product;
    }

    public Integer getCategory(){
        return category;
    }
    public void setCategory(Integer category){
        this.category = category;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Date getKakeibo_date() {
        return kakeibo_date;
    }
    public void setKakeibo_date(Date date){
        this.kakeibo_date = date;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public Timestamp getCreated_at(){
        return created_at;
    }
    public void setCreated_at(Timestamp created_at){
        this.created_at = created_at;
    }
    public Integer getCost(){
        return cost;
    }
    public void setCost(Integer cost){
        this.cost = cost;
    }


}
