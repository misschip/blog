package com.cos.blog.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

// 1234 -> 해쉬 ABCDJKEJKL#%#%#%#@@243242

// 해쉬암호 : SHA256, HMAC256
// 암호화+복호화 : Base64

//////////// 설명 ////////////
// for문 내 연산 방식
// 한 바이트 숫자(16진수 두자리수)에 256(0x100)을 더했다가 substring(1)로 맨 앞 숫자 1을 잘라내는 이유
//	- 원래의 한 바이트 숫자가 0xF 이하인 경우에 그 앞에 0을 붙이는 결과를 가져오기 위함
////////////////////////////
public class SHA256Test {
	
	private final static String mSalt = "코스";
	
    public static String encodeSha256(String source) {
        String result1 = "";
        String result2 = "";
        String result3 = "";
        
        byte[] a = source.getBytes();
        byte[] salt = mSalt.getBytes();
        byte[] bytes = new byte[a.length + salt.length];
        
        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            
            byte[] byteData = md.digest();
            
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sb2 = new StringBuffer();
            StringBuffer sb3 = new StringBuffer();
            
            // 이게 정석적인 처리방법임
            for (int i = 0; i < byteData.length; i++) {
                sb1.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            
            result1 = sb1.toString();
            System.out.println("result1 : " + result1);
            
            // substring(1)을 생략할 경우 한 바이트의 숫자가 무조건 1xx이라는 세자리 숫자로 출력됨을 보여줌
            // 이것은 256을 더했기 때문이다!
            for (int i = 0; i < byteData.length; i++) {
                sb2.append(Integer.toString((byteData[i] & 0xFF) + 256, 16));
            }
            
            result2 = sb2.toString();
            System.out.println("result2 : " + result2);
            
            // 원래의 한 바이트 숫자가 0xF 이하인 경우에 256을 더한 후 substring(1)하는 과정을 생략할 경우에
            // 그 앞에 0이 붙지 않고 한자리수로만 출력됨을 보여줌
            for (int i = 0; i < byteData.length; i++) {
            	if ((byteData[i] & 0xFF) <= 0xF) sb3.append('_');
                sb3.append(Integer.toString((byteData[i] & 0xFF), 16));
            }
            
            result3 = sb3.toString();
            System.out.println("result3 : " + result3);
            
            
            ///////////// salt를 추가하지 않은 비밀번호 해시화의 위험성 확인 /////////////
            md.update(source.getBytes());
            byte[] noSaltData = md.digest();
            String result4 = "";
            
            StringBuffer sb4 = new StringBuffer();
            
            // for문 처리는 정석적인 처리방법에 따름
            for (int i = 0; i < noSaltData.length; i++) {
                sb4.append(Integer.toString((noSaltData[i] & 0xFF) + 256, 16).substring(1));
            }
            
            result4 = sb4.toString();
            System.out.println("result4 : " + result4);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return result1;
    }
    
    @Test
    public void SHA256Test() {
    	String result = SHA256Test.encodeSha256("12345");
    	System.out.println(result);
    	
    }
}

/*
 *   http://hashtoolkit.com/common-passwords/
 */
/*
	result4 출력결과
		- 사람들이 쓰는 비밀번호가 실제로는 별로 다양하지 않다고 하는 사실과
			(https://en.wikipedia.org/wiki/Wikipedia:10,000_most_common_passwords)
		- 구글에서 일반적 비밀번호 -> SHA256 해시 결과값이 대량으로 검색 가능하다는 사실이 결합되면
		==> 결과적으로는 구글에서 해시값 검색만으로 장시간의 계산과정을 건너뛰고 원래의 비밀번호를 쉽게 알아낼 수 있는 상황이 된다.
		
		====> 따라서, 원래  비밀번호에 salt 값을 첨가하여 해시하는 게 보안을 위해서 아주 중요하다.
*/