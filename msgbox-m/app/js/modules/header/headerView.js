App.HeaderSView = App.BaseView.extend({
    templateName:'header'
//    mainMenuStatus: 'closed',
//    mainMenuAnimation: 'complete',
//    mainMenuHeight: '',
//    headerHeight: '',
//    
//    didInsertElement: function() {
//    	var self = this;
//
//		self.mainMenuHeight = $('.mainMenuWrapper').height() + 3;
//		self.headerHeight = $('.headerOuterWrapper').height();
//		$('.mainMenuWrapper').css('top', self.headerHeight - self.mainMenuHeight);
//		//$('.websiteWrapper').css('min-height', this.headerHeight + this.mainMenuHeight);
//    	
//    	$('.mainMenuButton').click(function() {
//
//		    if (self.mainMenuStatus == 'closed' && self.mainMenuAnimation == 'complete') {
//
//				self.mainMenuAnimation = 'incomplete';
//				$('.mainMenuWrapper').css('display', 'block');
//				$('.mainMenuWrapper').stop(true, true).animate({
//					top : self.headerHeight
//				}, 600, 'easeOutQuart', function() {
//					self.mainMenuStatus = 'open';
//					self.mainMenuAnimation = 'complete'
//				});
//
//			} else if (self.mainMenuStatus == 'open' && self.mainMenuAnimation == 'complete') {
//
//				self.mainMenuAnimation = 'incomplete';
//				$('.mainMenuWrapper').stop(true, true).animate({
//					top : self.headerHeight - self.mainMenuHeight
//				}, 600, 'easeInQuart', function() {
//					self.mainMenuStatus = 'closed';
//					self.mainMenuAnimation = 'complete';
//					$('.mainMenuWrapper').css('display', 'none');
//				});
//
//			}
//    		
//    		return false;
//    	});	
//    }
});