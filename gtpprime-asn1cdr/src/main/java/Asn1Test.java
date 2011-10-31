import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.snickers.asn1.Compiler;
import org.apache.snickers.asn1.parser.ASN1Compiler;
import org.apache.snickers.asn1.parser.ASN1Lexer;
import org.apache.snickers.asn1.parser.ASN1Parser;
import org.apache.snickers.asn1.stages.codegen.CodeGeneratorStage;
import org.apache.snickers.asn1.stages.parser.Environment;
import org.apache.snickers.asn1.stages.parser.ParseStage;

/**
 * 
 */

/**
 * @author krychu
 *
 */
public class Asn1Test {

	public static void generateTestLdap() {
		
	}
	
	public static void main(String[] args) {
		try {
			//ASN1Lexer lexer = new ASN1Lexer(new FileInputStream("./src/main/resources/asn1/32.298/ps.asn1"));
			//ASN1Lexer lexer = new ASN1Lexer(new FileInputStream("./src/main/resources/asn1/ldap.asn"));
		/*	ASN1Lexer lexer = new ASN1Lexer(new FileInputStream("./src/main/resources/asn1/NBAP-25433-530.asn1"));
			ASN1Parser parser = new ASN1Parser(lexer);
			
			Environment env = parser.compile();
			
			ASN1Compiler comp = new ASN1Compiler();*/
			
		Compiler compiler = new Compiler();
        Map environment = new HashMap();
                
//        environment.put( ParseStage.SRC_URI, "file:///home/krychu/workspace/OpenCDF/gtpprime-asn1cdr/src/main/resources/asn1/ldap.asn");
        //environment.put( ParseStage.SRC_URI, "file:///home/krychu/workspace/OpenCDF/gtpprime-asn1cdr/src/main/resources/asn1/32.298/ps.asn1");
        environment.put( ParseStage.SRC_URI, "file:///home/krychu/workspace/OpenCDF/gtpprime-asn1cdr/src/main/resources/asn1/3gpp_merged/huawei_ps.asn1");
        //environment.put( ParseStage.SRC_URI, "file:///home/krychu/workspace/OpenCDF/gtpprime-asn1cdr/src/main/resources/asn1/NBAP-25433-530.asn1");
        environment.put( CodeGeneratorStage.ROOT_PACKAGE, "org.apache.ldap.asn1" );
        environment.put( CodeGeneratorStage.DESTINATION_PATH, "./c/" );

        compiler.compile( environment );
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
