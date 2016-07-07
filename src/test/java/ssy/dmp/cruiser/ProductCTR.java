package ssy.dmp.cruiser;

import com.alibaba.fastjson.JSONObject;
import ssy.dmp.cruiser.annotation.Column;
import ssy.dmp.cruiser.annotation.HTable;
import ssy.dmp.cruiser.enumeration.Encode;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/6
 * Time: 下午5:52
 */
@HTable(tableName = "hbase_ads_yangmi_product_ctr_1d")
public class ProductCTR extends CTRBase {

    @Column(columnFamily = "T",qualifier = "product_id", encode = Encode.String)
    private long productId;

    public long getProductId() {
	return productId;
    }

    public void setProductId(long productId) {
	this.productId = productId;
    }

    @Override
    public String toString() {
	return this.toJson().toString();
    }

    public JSONObject toJson(){
	JSONObject json = new JSONObject();
	json.put("productId",this.productId);
	json.put("date",this.date);
	json.put("pv",this.pv);
	json.put("uv",this.uv);
	json.put("click",this.click);
	json.put("clickUsers",this.clickUsers);
	json.put("clickRate",this.clickRate);
	return json;
    }
}
