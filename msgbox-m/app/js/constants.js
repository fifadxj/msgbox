BoxConstants = {};
BoxConstants.connectionTimeoutValue = 60;
BoxConstants.webserviceUrl = '@@webserviceUrl';
BoxConstants.services = {
	login: BoxConstants.webserviceUrl + 'security/login',
	logout: BoxConstants.webserviceUrl + 'security/logout',
	
	messageList: BoxConstants.webserviceUrl + 'message/list',
	messageDetails: BoxConstants.webserviceUrl + 'message/view'
}