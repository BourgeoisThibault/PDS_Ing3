# Projet de synthèse ING3

To comment

# Use secure PayFree
  
  There is a REST server with secure configuration. You can access to the server with the link `https://ws.esibank.inside.esiag.info/`
  
  For use this fonctionnality, you must have the client `payfree-client` who can communicate with the server.
  
  The communication is securize with TLS protocol. All request are sign with password and only requests will be executed.
  
  All REST methods are available with Swagger API `http://api.esibank.inside.esiag.info`
  
  _For Dockerize the client, you can use this command `curl http://api.esibank.inside.esiag.info/install/script_install.sh | sudo bash`._
  
  
# Deployment diagram PayFree (+DAB)

![alt text](http://gitlab.esibank.inside.esiag.info/esibank-project/project-esibank/raw/master/diagrammes/UC_RetraitSansContact/ComponentDiagramGlobal.jpg) 



# Component Diagram PayFree (+DAB)

![alt text](http://gitlab.esibank.inside.esiag.info/esibank-project/project-esibank/raw/master/diagrammes/UC_RetraitSansContact/DeploymentDiagramGlobal.jpg) 
