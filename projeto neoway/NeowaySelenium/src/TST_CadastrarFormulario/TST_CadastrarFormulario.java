package TST_CadastrarFormulario;


import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import br.com.neoway.entidades.Formulario;;


public class TST_CadastrarFormulario {

	
	private WebDriver driver;
	private String baseUrl;	
	private StringBuffer verificationErrors = new StringBuffer();
	private Formulario formulario;
	
	
	@Before
	public void setUp() throws Exception {	
		
		this.formulario = new Formulario();
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//1 - Acessar a p�gina <http://demoqa.com/>
		this.baseUrl = "http://demoqa.com/";
		this.driver.get(baseUrl);
    
	}

	
	@Test
	public void cadastrarFormulario() throws Exception {
				    
		try{					
			
			//2 - Clicar no bot�o 'Registration'
			driver.findElement(By.id("menu-item-374")).click();				
	
			//3 - Preencher/selecionar os campos conforme a tabela abaixo:
			driver.findElement(By.id("name_3_firstname")).sendKeys(formulario.getFirtName());
			driver.findElement(By.id("name_3_lastname")).sendKeys(formulario.getLastName());
			driver.findElement(By.name("checkbox_5[]")).click();
			driver.findElement(By.id("phone_9")).sendKeys(formulario.getPhoneNumber());
			driver.findElement(By.id("username")).sendKeys(formulario.getUserName());
			driver.findElement(By.id("email_1")).sendKeys(formulario.geteMail());
			driver.findElement(By.id("password_2")).sendKeys(formulario.getPassword());
			driver.findElement(By.id("confirm_password_password_2")).sendKeys(formulario.getConfirmPassword());
		
			//4 - Clicar no bot�o Submit
			driver.findElement(By.name("pie_submit")).click();
			
			//5 � O sistema deve exibir a mensagem �Error: Username already exists�.
			String msgRetornadaInterface = driver.findElement(By.xpath("/html/body/div/div/div[1]/main/article/div/p")).getText();
			String msgEsperada = "Error: Username already exists";
		
			try{
				
				//Valida��o da mensagem
				assertEquals(msgEsperada, msgRetornadaInterface);	
				System.out.println("Aprovado");
				
			}catch (AssertionError erroValidaMensagemCadastroDeAvaliado){
    				
				System.out.println("Reprovado");
    			throw erroValidaMensagemCadastroDeAvaliado;
			
    		}finally{
    
    			System.out.println("Finalizado");
			
			}
			
		}catch(Exception erro){
			
			System.out.println("Erro ao Executar o cado de Teste: " + erro);
			System.out.println("Reprovado");
			System.out.println("Finalizado");
			
			throw erro;
			
		}
	}
	

	@After
	public void tearDown() throws Exception {
		
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		
		if (!"".equals(verificationErrorString)) {
			
			fail(verificationErrorString);
		}
	}
	
}
