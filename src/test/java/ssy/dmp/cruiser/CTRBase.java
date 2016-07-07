package ssy.dmp.cruiser;

import ssy.dmp.cruiser.annotation.Column;
import ssy.dmp.cruiser.enumeration.Encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/6
 * Time: 下午5:50
 */
public class CTRBase {

    @Column(columnFamily = "T", qualifier = "stat_date", encode = Encode.String)
    protected String date;		//日期

    @Column(columnFamily = "T", qualifier = "pv",encode = Encode.String)
    protected long pv;			//pv
    @Column(columnFamily = "T", qualifier = "uv", encode = Encode.String)
    protected long uv;			//uv

    @Column(columnFamily = "T", qualifier = "clk", encode = Encode.String)
    protected long click;		//点击数据

    @Column(columnFamily = "T", qualifier = "clk_uv", encode = Encode.String)
    protected long clickUsers;		//点击用户数

    protected String clickRate;		//点击率

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getPv() {
        return pv;
    }

    public void setPv(long pv) {
        this.pv = pv;
    }

    public long getUv() {
        return uv;
    }

    public void setUv(long uv) {
        this.uv = uv;
    }

    public long getClick() {
        return click;
    }

    public void setClick(long click) {
        this.click = click;
    }

    public long getClickUsers() {
        return clickUsers;
    }

    public void setClickUsers(long clickUsers) {
        this.clickUsers = clickUsers;
    }

    public String getClickRate() {
        return clickRate;
    }

    public void setClickRate(String clickRate) {
        this.clickRate = clickRate;
    }
}
