package cn.wj.bean;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-11-24
 */
public class SysPermission {
    /**
     * ����
     */
    private Long id;

    /**
     * ��Դ����
     */
    private String name;

    /**
     * ��Դ���ͣ�menu,button,
     */
    private String type;

    /**
     * ����url��ַ
     */
    private String url;

    /**
     * Ȩ�޴����ַ���
     */
    private String percode;

    /**
     * �����id
     */
    private Long parentid;

    /**
     * �����id�б�
     */
    private String parentids;

    /**
     * �����
     */
    private String sortstring;

    /**
     * �Ƿ����,1�����ã�0������
     */
    private String available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode == null ? null : percode.trim();
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getParentids() {
        return parentids;
    }

    public void setParentids(String parentids) {
        this.parentids = parentids == null ? null : parentids.trim();
    }

    public String getSortstring() {
        return sortstring;
    }

    public void setSortstring(String sortstring) {
        this.sortstring = sortstring == null ? null : sortstring.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}