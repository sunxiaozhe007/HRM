package domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {

    private int id;//���
    private String title;//����
    private String fileName;//�ļ���
    private MultipartFile file;//�ļ�
    private String remark;//����
    private Date createDate;//�ϴ�ʱ��
    private User user;//�ϴ���

    public Document(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", fileName='" + fileName + '\'' +
                ", file=" + file +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", user=" + user +
                '}';
    }
}
