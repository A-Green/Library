/**
 * Modal windows
 */

/**
 * Modal window for editing tags
 */
function showTagsModal() {
	
	var overlay = $("<div>").addClass('overlay');
	var popup = $("<div>").addClass("popup");
	var wrapper = $("<div>").addClass("modal-wrapper");
	
	var close_order = $("<div>").addClass("close_order").text("x").click(function() {
		removePopup();
	});
	
	var p = formTagList();
	var addMenu = $("<div>").addClass("add-tag-wrapper");
	
	var addTagField = $("<input>").attr("type", "text").attr("id", "tagField");	
	var addButton = $("<button>").addClass("add-btn").text(jQuery.i18n.prop('label.add')).click(function() {
		var tagName = $("#tagField").val();
		
		if (validateTag(tagName)) {

		var tag = createNewsTag(tagName);		
		saveNewsTag(tag);
		}
	});
	
	addMenu.append(addButton);
	addMenu.append(addTagField);
	
	var btnWrapper = $("<div>").addClass("table-buttons-wrapper");
	var saveBtn = $("<button>").addClass("table-edit-btn").text(jQuery.i18n.prop('label.save'))
	.click(function() {

		$('#newsTags :selected').each(function(i, selected){ 
			
			var li = $("<li>").text($(selected).text());
			$(".tags-menu").children("ul").prepend(li);
		});
		
		removePopup();
		
	});
	var cancelBtn = $("<button>").addClass("table-edit-btn").text(jQuery.i18n.prop('label.cancel'))
	.click(function() {
		removePopup();
	});	
	
	btnWrapper.append(saveBtn);
	btnWrapper.append(cancelBtn);
	
	overlay.appendTo('body');
	popup.append(close_order);
	wrapper.append(p);
	wrapper.append(addMenu);
	popup.append(wrapper);
	popup.append(btnWrapper);
	popup.appendTo('body');
	
	$('.popup .close_order, .overlay').click(function (){
		$('.popup, .overlay').css('opacity','0');
		$('.popup, .overlay').css('visibility','hidden');
		});
};

function createNewsTag(tagName) {
	
	return tag = {
		tagName : tagName,
	};
}

function createNewsAuthor(id, name) {
	
	return author = {
			"id" : id,
			"name" : name
	};
}

function saveNewsTag(tag) {

	var tagName = tag.tagName;
	
	$.ajax({
		url : "/News/tags/" ,
		type : 'POST',
	    data: JSON.stringify(tag),
	    datatype : "json",
	    contentType: "application/json; charset=utf-8",
		success : function(result) {  
			var newOption = $("<option>").attr("value", tagName).text(tagName);
			$("#newsTags").append(newOption);
		},
		error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
}

function removePopup() {
	$(".overlay").remove();
	$(".popup").remove();
}
/**
 * Modal window for editing authors
 */
function showAuthorsModal() {
	
	var overlay = $("<div>").addClass('overlay');
	var popup = $("<div>").addClass("popup");
	var wrapper = $("<div>").addClass("modal-wrapper");
	
	var close_order = $("<div>").addClass("close_order").text("x").click(function() {
		removePopup();
	});
	
	var p = formAuthorsList();
	
	var addMenu = $("<div>").addClass("add-author-wrapper");
	var addAuthorField = $("<input>").attr("type", "text").attr("id", "authorField");
	
	var addButton = $("<button>").addClass("add-btn").text(jQuery.i18n.prop('label.add')).click(function() {
		var authorName = $("#authorField").val();
		
		if (validateAuthor(authorName)) {
			var author = {
					"name" : authorName
			};
			
			saveAuthor(author);
		}
	});
	
	addMenu.append(addButton);
	addMenu.append(addAuthorField);
	
	var btnWrapper = $("<div>").addClass("table-buttons-wrapper");
	var saveBtn = $("<button>").addClass("table-edit-btn").text(jQuery.i18n.prop('label.save'))
	.click(function() {
		
		$('#newsAuthors :selected').each(function(i, selected){ 

			var li = $("<li>").attr("value", $(selected).val()).text($(selected).text());
			$(".authors-menu").children("ul").prepend(li);
		});
		
		removePopup();
		
	});
	
	var cancelBtn = $("<button>").addClass("table-edit-btn").text(jQuery.i18n.prop('label.cancel'))
	.click(function() {
		removePopup();
	});	
	
	btnWrapper.append(saveBtn);
	btnWrapper.append(cancelBtn);
	
	overlay.appendTo('body');
	popup.append(close_order);
	wrapper.append(p);
	wrapper.append(addMenu);
	popup.append(wrapper);
	popup.append(btnWrapper);
	popup.appendTo('body');
	
	$('.popup .close_order, .overlay').click(function (){
		$('.popup, .overlay').css('opacity','0');
		$('.popup, .overlay').css('visibility','hidden');
		});	
}

function saveAuthor(author) {
	
	$.ajax({
		url : "/News/authors/" ,
		type : 'POST',
	    data: JSON.stringify(author),
	    datatype : "json",
	    async: false,
	    contentType: "application/json; charset=utf-8",
		success : function(result) {  			
			var newOption = $("<option>").attr("value", result).text(author.name);
			$("#newsAuthors").append(newOption);
		},
		error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
}

/**
 * Modal window for error 
 */
function showErrorModal(status) {
	
	var overlay = $("<div>").addClass('overlay');
	var popup = $("<div>").addClass("popup");
	var wrapper = $("<div>").addClass("modal-wrapper");
	
	var close_order = $("<div>").addClass("close_order").text("x").click(function() {
		removePopup();
	});
	
	var p = $("<p>").addClass("error-message").text(getErrorMessage(status));
	
	var listLink = $("<a>").attr("href", "#")
	.addClass("return-link")
	.text(jQuery.i18n.prop('msg.returnToNewsList'))
	.click(function() {
		placeAll(_pageConfig.newsPerPage, 0);
		removePopup();
		$('.changeLang').unbind().click(function () {
			placeAll(_pageConfig.newsPerPage, 0);
		});
	});
	
	var btnWrapper = $("<div>").addClass("table-buttons-wrapper");
	var saveBtn = $("<button>").addClass("table-edit-btn").text(jQuery.i18n.prop('label.ok'))
	.click(function() {	
		removePopup();		
	});
		
	btnWrapper.append(saveBtn);
	
	overlay.appendTo('body');
	popup.append(close_order);
	wrapper.append(p);
	wrapper.append(listLink);
	popup.append(wrapper);
	popup.append(btnWrapper);
	popup.appendTo('body');
	
	$('.popup .close_order, .overlay').click(function (){
		$('.popup, .overlay').css('opacity','0');
		$('.popup, .overlay').css('visibility','hidden');
		});	
}

function getErrorMessage(status) {
	
	var message = "";
	
	switch (status) {
	case 500:
		message = jQuery.i18n.prop('error.err500');
		break;
	case 404:
		message = jQuery.i18n.prop('error.err404');
		break;
	case 400:
		message = jQuery.i18n.prop('error.err400');
		break;
	default:
		message = jQuery.i18n.prop('error.unexpected');
	}
	
	return message;
}