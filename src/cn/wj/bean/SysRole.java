package cn.wj.bean;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-11-24
 */
public class SysRole {
    private String id;

    private String name;

    /**
     * �Ƿ����,1�����ã�0������
     */
    private String available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}