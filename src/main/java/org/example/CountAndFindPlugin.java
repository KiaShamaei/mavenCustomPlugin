package org.example;

import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "findDependencyCount", defaultPhase = LifecyclePhase.COMPILE)
public class CountAndFindPlugin extends AbstractMojo {
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;
    @Parameter(property = "artifactId")
    private String artifactId;
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        List<Dependency> dependencies = project.getDependencies();
        long numDependencies = dependencies.stream().count();
        getLog().info("Number of dependencies: " + numDependencies);
        try {
            if (artifactId != null) {
                long foundByArtifact = dependencies.stream()
                        .filter(node -> {
                            System.out.println(node.getArtifactId());
                            return node.getArtifactId().equals(artifactId);
                        }).count();
                if (foundByArtifact > 0) {
                    getLog().info("Found dependency with artifactId: " + artifactId + "count : " + foundByArtifact);
                } else {
                    getLog().info("Dependency with artifactId " + artifactId + " not found.");
                }
            }
        }catch (Exception e){
            throw new MojoExecutionException("Error counting dependencies", e);
        }
    }
}
