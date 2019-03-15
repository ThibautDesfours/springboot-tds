package sa4.spring.td3.components;
import java.io.IOException;
import io.github.jeemv.springboot.vuejs.components.VueComponent;
public class ConfirmButton {

	public static void main(String[] args) throws IOException{
		VueComponent compo = new VueComponent("confirm-button");
		compo.setDefaultTemplateFile();
		compo.createFile(false);
	}
}
