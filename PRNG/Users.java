
import java.security.MessageDigest;
import java.util.Random;
import java.nio.charset.Charset;
public class Users {
	private int id;
	public byte[] block = new byte[1048576];
	private byte[] randomNum = new byte[64];
	
	public Users(int id){
		this.id = id;
	    new Random().nextBytes(this.block);
	    new Random().nextBytes(this.randomNum);
	}

	public int getID(){
		return id;
	}
	public byte[] getRan(){
		return randomNum;
	}
	public byte[] getHashedRan()throws Exception{
		return sha3Hash(randomNum);
	}
	
	
	private byte[] sha3Hash(byte[] b) throws Exception{
		MessageDigest digest = MessageDigest.getInstance("SHA3-512");
		return digest.digest(b);
	}

}
