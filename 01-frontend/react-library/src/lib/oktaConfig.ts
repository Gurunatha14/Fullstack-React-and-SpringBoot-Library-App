export const oktaConfig = {
    clientId:'0oaiosk6eeqlXxVUr5d7',
    issuer:'https://dev-59698608.okta.com/oauth2/default',
    redirectUri:'http://localhost:3000/login/callback',
    scopes:['openid','profile','email'],
    pkce:true,
    disableHttpsCheck:true,
}