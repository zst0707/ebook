package gcxy.zst.pager;

import java.util.List;

/**
 * @ClassName PageBean
 * @Description TODO
 * @Author 郑思婷
 * @Date 2019/9/208:37
 * @Version 1.0
 **/
public class PageBean<T> {
    //分页Bean
    private int pc;//当前页码
    private int tr;//总记录数
    private int ps;//每页记录数
    private String url;//请求路径和参数，例如：/BookServlet?method=findXXX&cid=1&bname=2
    private List<T> beanList;
    // 计算总页数
    public int getTp(){
        int i=tr%ps;
        if(i==0){
            i=tr/ps;
        }else {
            i=tr/ps+1;
        }
        return i;
    }
    public int getPc() {
        return pc;
    }
    public void setPc(int pc) {
        this.pc = pc;
    }
    public int getTr() {
        return tr;
    }
    public void setTr(int tr) {
        this.tr = tr;
    }
    public int getPs() {
        return ps;
    }
    public void setPs(int ps) {
        this.ps = ps;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public List<T> getBeanList() {
        return beanList;
    }
    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }
}
