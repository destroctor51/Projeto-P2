package disco;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Essa classe disponibiliza operacoes estaticas para lidar com o armazenamento e recuperacao de informacoes em arquivos, de forma criptografada.
 * 
 * @author Victor Andrade de Almeida
 */
public final class Arquivo {
	
	private static SecretKey key;
	
	private Arquivo() {}
	
	private static void initKey() {
		try {
			byte[] preSeed = "(d012Çço^K)(DsuP0AUS2ijAOdjiisodAPS~´d".getBytes();
			SecureRandom seed = new SecureRandom(preSeed);
			KeyGenerator generator = KeyGenerator.getInstance("AES");
			generator.init(seed);
			key = generator.generateKey();
		} catch (NoSuchAlgorithmException e) {}
	}
	
	/**
	 * Salva um Object qualquer que implemente Serializable em um arquivo, de modo nao legivel por olhos humanos.
	 * <p>
	 * Se ja existir um arquivo de mesmo nome no caminho especificado, esse arquivo sera sobrescrito.
	 * 
	 * @param alvo  o objeto a ser salvado
	 * @param arquivo  o caminho do arquivo destino
	 * @return true se a operacao foi concluida com sucesso, false caso contrario
	 */
	public static boolean salvaObjeto(Object alvo, String arquivo) {
		if(key == null) initKey();
		ObjectOutputStream out = null;
				
		try {
			Cipher encryptCipher = Cipher.getInstance("AES");
		    encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		    
		    out = new ObjectOutputStream(new CipherOutputStream(
		    		new FileOutputStream(arquivo), encryptCipher));
		    
		    out.writeObject(alvo);
		    out.flush();
		}
		
		catch (Exception e) {
			deleta(arquivo);
			return false;
		}
		
		finally {
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					deleta(arquivo);
				}
		}
		
		return true;
	}
	
	/**
	 * Recupera um Object salvo usando o metodo salvaObjeto.
	 * 
	 * @param arquivo  o caminho do arquivo a ser lido
	 * @return o objeto salvo no arquivo, ou null caso haja algum problema na leitura
	 */
	public static Object carregaObjeto(String arquivo) {
		if(key == null) initKey();
		ObjectInputStream in = null;
		Object leitura = null;
		
		try {
			Cipher encryptCipher = Cipher.getInstance("AES");
		    encryptCipher.init(Cipher.DECRYPT_MODE, key);
		    
		    in = new ObjectInputStream(new CipherInputStream(
		    		new FileInputStream(arquivo), encryptCipher));
		    
		    leitura = in.readObject();
		} catch (Exception e) {}
		
		finally {
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {}
		}
		
		return leitura;
	}
	
	/**
	 * Deleta o arquivo especificado.
	 * 
	 * @param arquivo  o caminho do arquivo a ser deletado
	 * @return true se o arquivo foi deletado com sucesso, false caso contrario
	 */
	public static boolean deleta(String arquivo) {
		try {
			Files.delete(Paths.get(arquivo));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
