
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * This goal will say a message.
 * 
 * @goal howdy-world
 */
public class HelloWorld extends AbstractMojo {

	public void execute() throws MojoExecutionException {

		getLog().info("Hello World !");
	}
}