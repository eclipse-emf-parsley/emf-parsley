# Mail RCP example

Import this project together with `org.eclipse.emf.parsley.examples.mail.model`.
The RCP project contains the application, all four Parsley modules (accounts,
unified folders, mails, and message), and the plug-in based product definition.

The Parsley sources are under `src/`. Maven runs the Xtext generator and places
derived Java in `emfparsley-gen/`. In Eclipse, the Xtext and Parsley builders
regenerate that output when a Parsley source changes.

Run `EmfParsleyMail.launch` to start the application. The product definition is
`EmfParsleyMail.product` in this project.
