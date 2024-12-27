package org.example;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "greet", defaultPhase = LifecyclePhase.COMPILE)
public class KiaPluginSample extends AbstractMojo {
    @Parameter(property = "name", defaultValue = "World")
    private String name;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
            getLog().info("Hello, " + name + "!");
        }
}
