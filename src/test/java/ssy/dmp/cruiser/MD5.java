package ssy.dmp.cruiser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA
 * Author: huangqian
 * Date: 16/6/6
 * Time: 下午6:32
 */
public enum MD5 {
    INSTANCE;

    private static final ThreadLocal<MessageDigest> holder;

    private MD5() {
    }

    public String encode2String(String source) {
	byte[] mdbytes = this.encode2Bytes(source);
	StringBuffer hexString = new StringBuffer();

	for (int i = 0; i < mdbytes.length; ++i) {
	    String hex = Integer.toHexString(255 & mdbytes[i]);
	    if (hex.length() == 1) {
		hexString.append('0');
	    }

	    hexString.append(hex);
	}

	return hexString.toString();
    }

    public byte[] encode2Bytes(String source) {
	if (source != null && source.length() > 0) {
	    MessageDigest md = (MessageDigest) holder.get();
	    byte[] btInput = source.getBytes();
	    md.reset();
	    md.update(btInput);
	    return md.digest();
	} else {
	    throw new IllegalArgumentException("message should not be empty");
	}
    }

    static {
	holder = new ThreadLocal() {
	    protected MessageDigest initialValue() {
		try {
		    return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException var2) {
		    throw new RuntimeException("Initial MD5 MessageDigest failed !!!");
		}
	    }
	};
    }
}