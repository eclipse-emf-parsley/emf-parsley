# Maven Central releases

Maven Central deployment uses the `maven` and `sonatype-oss-release` profiles.
Central releases must not use `-SNAPSHOT` versions. From the repository root,
prepare release versions with the existing `set-version-release` profile:

```sh
./mvnw -f releng/org.eclipse.emf.parsley.parent/pom.xml -N \
  -Dtycho.mode=maven -Pset-version-release \
  org.apache.maven.plugins:maven-antrun-plugin:run
```

Configure a server with ID `central` in your Maven `settings.xml`, using a
[Central Portal user token](https://central.sonatype.org/publish/generate-portal-token/)
as its username and password. The release profile also requires a GPG signing key
for `maven-gpg-plugin`.

```sh
./mvnw -f releng/org.eclipse.emf.parsley.parent/pom.xml \
  -Pmaven,sonatype-oss-release clean deploy
```

The Maven examples are built and tested but excluded from deployment through
`maven.deploy.skip`, which is also honored by the Central publishing plugin.

The plugin uploads the artifacts and waits for validation. Automatic publishing
is disabled: review the deployment and publish it manually in the
[Central Portal](https://central.sonatype.com/publishing/deployments).
