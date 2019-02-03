package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void TestCommits() throws IOException {
    Github github = new RtGithub("e52174631d773e87142b4a1635104e509433af98");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("NastasjaG", "java_pft")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
