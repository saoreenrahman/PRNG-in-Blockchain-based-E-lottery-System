
import java.security.Provider;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
public class Test {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Usage: java (Test) (number of user join)");
			System.exit(-1);
		}
		if(Integer.parseInt(args[0])<10){
			System.out.println("Too less people");
			System.exit(-1);
		}
		ArrayList<Ticket> vaildTicket = new ArrayList<Ticket>();
		ArrayList<Ticket> collectHash = new ArrayList<Ticket>();

		try {
			System.out.println("Commit Phase Start");
			//commit, time limits in this step
			for(int i = 0; i<Integer.parseInt(args[0]);i++){
				Users user = new Users(i);
				boolean vaild = true;
				for(Ticket t: collectHash){
					if(Arrays.equals(user.getHashedRan(),t.hash)){
						System.out.println("User "+i+": unable to purchase this number.\n Number: ");
						vaild = false;
						break;
					}
				}
				if(vaild){
					collectHash.add(new Ticket(user,user.getHashedRan()));
					//System.out.println("user: "+user.getID()+"\nHashed Random Number: "+ user.getHashedRan());
				}
			}
			System.out.println("Commit Phase Finished\n------------------------------------------------\nReveal Phase Start");
			//reveal
			for(Ticket t: collectHash){
				boolean vaild = Arrays.equals(toSha3(t.user.getRan()),t.hash);
				if(vaild){
					vaildTicket.add(t);
					System.out.println("user: "+t.user.getID()+"   Their Random number: "+ Integer.toUnsignedLong(ByteBuffer.wrap(t.user.getRan()).getInt()));
				}else{	
					System.out.println("not matching the hash value");
				}
			}
			System.out.println("Reveal Phase Finished\n------------------------------------------------");
			//calculation
			byte[] randomNum = new byte[0];
			//System.out.println(toBinStr(randomNum));
			Collections.reverse(vaildTicket);
			for(Ticket t: vaildTicket){
				randomNum = toSha3(concat(randomNum,t.user.getRan()));
			}
			//sha3(s1|sha3(s2|sha3(s3|sha3(s4|...sha3(sn-1|sha3(sn))))))
			Collections.reverse(vaildTicket);
			byte[] lastBlock = new byte[1048576];
			new Random().nextBytes(lastBlock);
			
			randomNum = toSha3(concat(randomNum,toSha3(lastBlock)));
			//sha3(s1|sha3(s2|sha3(s3|sha3(s4|...sha3(sn-1|sha3(sn)))))|blockhash)
			
			//System.out.println(toHex(randomNum));
			
			System.out.println("The Random Number in Binary: "+toBinStr(randomNum)+"\n");
			System.out.println("The Random Number in Decimal: "+Integer.toUnsignedLong(ByteBuffer.wrap(randomNum).getInt())+"\n");
			
			int winner = ((ByteBuffer.wrap(randomNum).getInt() % vaildTicket.size())+ vaildTicket.size())% vaildTicket.size();
			
			System.out.println("The Winner ID: "+vaildTicket.get(winner).user.getID()+"\n");

			
			//Testing
			System.out.println("Frequency Test: "+testing("python 02_frequency_within_block_test.py "+toBinStr(randomNum)));
			System.out.println("Runs Test: "+ testing("python 03_runs_test.py "+toBinStr(randomNum)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static byte[] toSha3(byte[] s)throws Exception{
		MessageDigest digest = MessageDigest.getInstance("SHA3-512");
		return digest.digest(s);
	}
	
	public static String toHex(byte[] examp)throws Exception{
		String out = "";
		for (byte b: examp){
			out+=String.format("%02x", b);
		}
		return out;
	}
	
	public static String toBinStr(byte[] examp){
		String out = "";
		for(byte b : examp){
			out+=String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		}
		return out;
	}
	
	public static byte[] longToBytes(long x) {
	    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
	    buffer.putLong(x);
	    return buffer.array();
	}

	public static byte[] concat(byte[] a, byte[] b){
		byte[] c = new byte[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}
	public static String testing(String command)throws Exception{
		String s = null;
		String result="";
		Process p = Runtime.getRuntime().exec(command);
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((s=in.readLine())!=null){
			result+=s;
		}
		return result;
	}
}

class Ticket{
	public Users user;
	public byte[] hash;
	public Ticket(Users u,byte[] h){
		user = u;
		hash = h;
	}
}
