/**
 * Client-side validation 
 */

var validationParamsHolder = {
	maxTitleLength : 100,
	maxBriefLength : 500,
	maxContentLength : 2048,
	maxCommentLength : 200,
	maxAuthorNameLength : 50,
	minAuthorNameLength : 2,
	minTagNameLength : 2,
	maxTagNameLength : 15,
};

function validateNewsMessage(newsMessage) {
	
	var isValid = true;
	
	$("p").remove();
	
	var titleLength = newsMessage.title.length;

	if (!titleLength)
	{
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.titleEmpty'));		
		$("#news-title").after(p);
		
	} else if (titleLength > validationParamsHolder.maxTitleLength ){
		
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.titleLarge'));		
		$("#news-title").after(p);
	}
	
	var briefLength = newsMessage.brief.length;
	
	if (!briefLength)
	{	
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.briefEmpty'));		
		$("#news-brief").after(p);
	} else if(briefLength > validationParamsHolder.maxBriefLength) {
		
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.briefLarge'));		
		$("#news-brief").after(p);
	}
		
	var contentLength = newsMessage.content.length;
	
	if (!contentLength)
	{	
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.contentEmpty'));		
		$("#news-content").after(p);
	} else if (contentLength > validationParamsHolder.maxContentLength) {
		
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.contentLarge'));		
		$("#news-content").after(p);
	}
	
	if (!newsMessage.tags.length) {
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.tagsRequired'));
		$(".tags-menu").append(p);
	}
	
	if (!newsMessage.authors.length) {
		isValid = false;
		var p = $("<p>").text(jQuery.i18n.prop('validation.authorRequired'));
		$(".authors-menu").append(p);
	}
	
	return isValid;
	
}

function validateTag(tag) {
	
	$(".add-tag-wrapper > p").remove();
	
	var valid = true;
	
	if (!tag.length || tag.length > validationParamsHolder.maxTagNameLength 
			|| tag.length < validationParamsHolder.minTagNameLength) {
		
		var p = $("<p>").text(jQuery.i18n.prop('validation.invalidTag'));
		$(".add-tag-wrapper").prepend(p);
		
		valid = false;
	}
	
	return valid;
}

function validateAuthor(authorName) {
	
	$(".add-author-wrapper > p").remove();
	
	var valid = true;
	
	if (!authorName.length || authorName.length > validationParamsHolder.maxAuthorNameLength
			|| authorName.length < validationParamsHolder.minAuthorNameLength) {

		var p = $("<p>").text(jQuery.i18n.prop('validation.invalidAuthor'));
		$(".add-author-wrapper").prepend(p);
		
		valid = false;
	}
	
	return valid;
}

function validateComment(comment) {

	var valid = true;
	
	if (!comment.text.length) {
		valid = false;
	} else if(comment.text.length > validationParamsHolder.maxCommentLength) {
		
		$(".comments-area > p").remove();
		
		var p = $("<p>").text(jQuery.i18n.prop('validation.commentLarge'));
		$("#comment-area").after(p);
		valid = false;
	}
	
	return valid;
}