const environment = require( "./environment" )

module.exports = {
  
  launchConfig: {
    args: [
      '--no-sandbox', 
      '--disable-setuid-sandbox'
    ]
  }
};