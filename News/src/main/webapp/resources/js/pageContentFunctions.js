/**
 * Provides navigation and managing news message functions.
 */

// on document ready
$(function() {
	applyLanguage('en');

	$('.changeLang').unbind().click(function () {
		placeAll(_pageConfig.newsPerPage, _pageConfig.currentNewsPage * _pageConfig.newsPerPage);
	});
	
	placeAll(_pageConfig.newsPerPage, _pageConfig.currentNewsPage * _pageConfig.newsPerPage);
	
	$("#header_title").click(function() {
		_pageConfig.loadNewsURL = " /News/news/?";
		
		$('.changeLang').unbind().click(function () {
			placeAll(_pageConfig.newsPerPage, 0);
		});
		
		placeAll(_pageConfig.newsPerPage, 0);
	});
});

/**
 * Creates comments paging area
 * @param newsId news message id
 * @param amount amount of comments to load
 * @param skip amount of comments that will be skipped
 */
function createCommentsPaging(newsId, amount, skip) {
	
	$("#comment-paging").remove();
	var area = $("<div>").addClass("paging-area").attr("id", "comment-paging");
	
	var result = getPagedComments(newsId, amount, skip);
	
	placePagedComments(result.comments);
	
    $(area).pagination({
        items: result.total,
    	currentPage: (skip / _pageConfig.commentsPerPage) + 1,
        itemsOnPage: _pageConfig.commentsPerPage,
        displayedPages: 3,
        edges: 1,
        onPageClick : function(pageNumber) {
        	
        	createCommentsPaging(newsId, _pageConfig.commentsPerPage, (pageNumber - 1) * _pageConfig.commentsPerPage);
        	_pageConfig.currentCommentsPage = pageNumber;
		},
        cssStyle: 'light-theme'
    });
    
    $(".comments-area").append(area);
}

/**
 * Places comments into wrapper
 * @param comments list of json comments
 */
function placePagedComments(comments) {
	
	$('#comments-wrapper').empty();

	$(comments).each(function() {  
		
		  var comment = $("<div>").addClass("comment");
		  var timestamp = moment.unix(this.creationDate / 1000);
		  var date = timestamp.format('DD/MM/YYYY, h:mm:ss');
	      var commentDate = $("<div>").addClass("comment-date").text(date);
	      var commentText = $("<div>").addClass("comment-text").text(this.text);
	      
	      comment.append(commentDate);
	      comment.append(commentText);
	      
	      $('#comments-wrapper').append(comment);   				
	});
}
/**
 * Load news message comments
 * @param newsId news message id
 * @param amount amount of comments to load
 * @param skip amount of comments to skip from loading
 * @returns json list of news comments 
 */
function getPagedComments(newsId, amount, skip) {
	
	var result = "";
	$.ajax({
		url : '/News/news/' + newsId + '/comments/?amount=' + amount + '&skip=' + skip,
		type : 'GET',
		async: false,
		contentType: 'json',
		success : function(data) {		
			result = data;
		},
		error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});	
	
	return result;
}

/**
 * Paging configuration
 */
var _pageConfig = {
		"newsPerPage" : 4,
		"currentNewsPage": 0,
		"loadNewsURL": "/News/news/?",
		"commentsPerPage" : 2,
		"currentCommentsPage" : 0,
		"extendedCommentsPerPage": 8,
		"extendedCommentsPage": 0
};

/**
 * Applies language to the page
 * @param lang language
 */
function applyLanguage(lang) {
	
	jQuery.i18n.properties({
	    name:'localization', 
	    path:'resources/localization/', 
	    mode:'both',
	    language:lang, 
	    callback: function() {
	    	$('#header_title').text(jQuery.i18n.prop('label.header'));
	    	$('#site-footer').text(jQuery.i18n.prop('label.footer'));
	    }
	});
}

function createViewCommentsHref(newsId) {

	var href = $('<a>')
			.attr("href", "#")
			.text(jQuery.i18n.prop('label.viewComments'))
			.click(function() {
				createEditCommentsPage(newsId);
				
				$('.changeLang').unbind().click(function () {
					createEditCommentsPage(newsId);				
				});
				
				$("#backHref").click(function() {
					showAddingForm(newsId);
				});				
	}) ;
	
	return href;
}

function createAddNewsHref() {
	
	var addNewsHref = $("<li>").append( $('<a>')
			.attr("href", "#")
			.text(jQuery.i18n.prop('label.addNews'))
			.click(function() {
				
				$('.changeLang').unbind().click(function () {
					showAddingForm();
				});
				
				showAddingForm();	 

	}) );
	
	return addNewsHref;
}

function createViewTopCommentedLink(top) {

	var link = $("<li>").append(
			$("<a>").attr("href", "#").text(
					jQuery.i18n.prop('label.top') + " " + top).click(
					function() {
						loadMostCommentedNews(top, _pageConfig.newsPerPage, 0);
					}));
	return link;
}

function createViewNewsHref() {

	var viewNewsListHref = $("<li>").append( $('<a>')
			.attr("href", "#")
			.text(jQuery.i18n.prop('label.listNews'))
			.click(function() {
		_pageConfig.loadNewsURL = "/News/news/?";	
		_pageConfig.currentNewsPage = 0;
		
		$('.changeLang').unbind().click(function () {
		    placeAll(_pageConfig.newsPerPage, _pageConfig.currentNewsPage * _pageConfig.newsPerPage);
		});
		
	    placeAll(_pageConfig.newsPerPage, 0);
	    
	}) );
	
	return viewNewsListHref;
}

function createBackHref() {
	
	var backHref = $("<li>").append( $('<a>')
			.attr("href", "#").attr("id", "backHref")
			.text(jQuery.i18n.prop('label.back'))
			.click(function() {
			placeAll(_pageConfig.newsPerPage, 0);
	}) );
	
	return backHref;
}

function createDeleteNewsHref(id) {
	
	var href = $("<li>").append( $('<a>')
			.attr("href", "#")
			.text(jQuery.i18n.prop('label.deleteNews'))
			.click(function() {		
				deleteNews(id);
	}) );
	
	return href;
}

function createEditNewsHref(id) {
	
	var href = $("<li>").append( $('<a>')
			.attr("href", "#")
			.text(jQuery.i18n.prop('label.editNews'))
			.click(function() {
			
				showAddingForm(id);
	}) );
	
	$('.changeLang').unbind().click(function () {
		showAddingForm(id);
	});

	return href;
}

/**
 * Places preview of news messages
 * @param amount amount of news 
 * @param skip amount of news messages to skip
 */
function placeAll(amount, skip) {

	$('#content').empty();
	$('#menu').contents().not('.nav-menu').remove();
	$('#nav-menu-links').empty();
	$('#nav-menu-links').append(createAddNewsHref());
	
	_pageConfig.loadNewsURL = " /News/news/?";	
	_pageConfig.currentNewsPage = 0;
	_pageConfig.currentCommentsPage = 0;
	
	createNewsGroupMenu();
	
	placeNews(_pageConfig.newsPerPage, skip);
}
/**
 * Places news messages preview with paging
 * @param amount amount of news 
 * @param skip amount of news messages to skip
 */
function placeNews(amount, skip) {
	
	$(".paging-area").remove();
	
	var area = $("<div>").addClass("paging-area");
	
	var data = getPagedNews(amount, skip);
	
	placePagedNews(data.news);
	
    $(area).pagination({
    	items: data.total,
    	currentPage: _pageConfig.currentNewsPage + 1,
        itemsOnPage: _pageConfig.newsPerPage,
        displayedPages: 3,
        edges: 1,
        onPageClick : function(pageNumber) { 
        	_pageConfig.currentNewsPage = pageNumber - 1;
        	placeNews(_pageConfig.newsPerPage, (pageNumber - 1) * _pageConfig.newsPerPage);

		},	
        cssStyle: 'light-theme'
    });
    
    $("#content").append(area);
}
/**
 * Places news messages preview 
 * @param data list of json news
 */
function placePagedNews(data) {
	
	$("#news-messages-wrapper").remove();
	
	var wrapper = $("<div>").attr("id","news-messages-wrapper");
	
	$(data).each(function() {  

		wrapper.append(formMessagePreview(
				this.id, 
				this.title, 
				this.modificationDate, //date, and another date
				this.brief ));
	});
	
	$('#content').append(wrapper);		
}
/**
 * Load news messages
 * @param amount amount of news 
 * @param skip amount of news messages to skip
 * @returns list of news messages in json
 */
function getPagedNews(amount, skip) {

	var result = "";

	$.ajax({
		url : _pageConfig.loadNewsURL + 'amount=' + amount + '&skip=' + skip,
		type : 'GET',
		contentType: "application/json; charset=utf-8",
		async: false,
		success : function(data) {
			result = data;
		},
		error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});	
	
	return result;
}
/**
 * Creates message preview
 * @param id
 * @param title
 * @param date
 * @param brief
 * @returns news preview
 */
function formMessagePreview(id, title, date, brief) {

	var timestamp = moment.unix(date / 1000);
	var a = timestamp.format('LL');

    var newsMessage = $("<div>").addClass("news-message");

    var newsDate = $("<div>").addClass("news-message-date").text(a);
    var newsTitle = $("<div>").addClass("news-message-title").text(title);
    var newsBrief = $("<div>").addClass("news-message-brief").text(brief);

    newsMessage.append(newsDate);
    newsMessage.append(newsTitle);
    newsMessage.append(newsBrief);

    newsMessage.append(formMessagePreviewLinks(id));

    return newsMessage;
}
/**
 * Creates news message preview links
 * @param id news message id
 * @returns
 */
function formMessagePreviewLinks(id) {

    var messageLinks = $("<div>").addClass("news-message-links");

    var viewLink = $("<a>").attr("href", "#").text(jQuery.i18n.prop('label.view'));
    viewLink.click(function() {
        viewNewsMessage(id);
        
    	$('.changeLang').unbind().click(function () {
            viewNewsMessage(id);
    	});
    });

    var editLink = $("<a>").attr("href", "#").text(jQuery.i18n.prop('label.edit'));
    editLink.click(function() {
        showAddingForm(id);
        
    	$('.changeLang').unbind().click(function () {
            showAddingForm(id);
    	});
    });

    var deleteLink = $("<a>").attr("href", "#").text(jQuery.i18n.prop('label.delete'));
    deleteLink.click(function() {
        deleteNews(id);
    });

    messageLinks.append(viewLink);
    messageLinks.append(editLink);
    messageLinks.append(deleteLink);

    return messageLinks;
}

/**
 * updates news message and place it to views news message page
 * @param newsId news message id
 */
function updateNews(newsId) {

	var title = $("#news-title").val();
	var brief = $("#news-brief").val();
	var content = $("#news-content").val();
	
	var tags = [];
	
	$(".tags-menu").children("ul").children("li").each(function(i) {
		
		var tag = {
				"tagName" : $(this).text()
		};
		
		tags[i] = tag;
	});
	

	var authors = [];

	$(".authors-menu").children("ul").children("li").each(function(i) {

		var author = {
				"id" : $(this).val(),
				"name" : $(this).text(),
		};

		authors[i] = author;
	});
	
	var newsMessage = createNewsMessage(title, brief, content, tags, authors);
	
	var isValid = validateNewsMessage(newsMessage);

	if (isValid) {
		
		$.ajax({
			url : "/News/news/" + newsId,
			type : 'PUT',
			data: JSON.stringify(newsMessage),
			datatype : "json",
			contentType: "application/json; charset=utf-8",
			success : function(result) {
				viewNewsMessage(result.id);			
			},
			error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	}
} 

/**
 * saves news message and place it to view news message page
 */
function saveNews() {

	var title = $("#news-title").val();
	var brief = $("#news-brief").val();
	var content = $("#news-content").val();
	
	var tags = [];
	
	$(".tags-menu").children("ul").children("li").each(function(i) {
		
		var tag = {
				"tagName" : $(this).text()
		};
		
		tags[i] = tag;
	});
	
	var authors = [];

	$(".authors-menu").children("ul").children("li").each(function(i) {

		var author = {
				"id" : $(this).val(),
				"name" : $(this).text(),
		};
		
		authors[i] = author;
	});
	
	var news = createNewsMessage(title, brief, content, tags, authors);
	var isValid = validateNewsMessage(news); 	
	
	if (isValid) {
		
		$.ajax({
			url : "/News/news/",
			type : 'POST',
		    data: JSON.stringify(news),
		    datatype : "json",
		    contentType: "application/json; charset=utf-8",
			success : function(result) {  
				viewNewsMessage(result.id); 
			},
			error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	} 
}

/**
 * deletes news message and showing all news
 */
function deleteNews(id) {
	
	if (confirm(jQuery.i18n.prop('msg.confirmDelete'))) {
		
		$.ajax({
		    url: '/News/news/' + id,
		    type: 'DELETE',
		    success: function(result) {
		    	placeAll(_pageConfig.newsPerPage, _pageConfig.currentNewsPage * _pageConfig.newsPerPage);
		    },
		    error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	}
}

/**
 * View single news message
 */
function viewNewsMessage(newsId) {

	$.ajax({
	    url: '/News/news/' + newsId,
	    type: 'GET',
	    async: false,
	    success: function(newsMessage) {
	    	
			$('#content').empty();
			$('#nav-menu-links').empty();
			
	    	$('#menu').contents().not('.nav-menu').remove();

			$('#nav-menu-links').append(createViewNewsHref());
			$('#nav-menu-links').append(createDeleteNewsHref(newsMessage.id));
			$('#nav-menu-links').append(createEditNewsHref(newsMessage.id));			
			$("#nav-menu-links").append(createBackHref());
	    	
	    	var view = formNewsView(newsMessage);
	    	var commentView = formNewsCommentsView(newsMessage.id);    	

			$('#content').append(view);  
			$('#content').append(commentView);  

	    	var pagingArea = createCommentsPaging(newsMessage.id, _pageConfig.commentsPerPage, 0);
		
	    	$(".comments-area").append(pagingArea);

	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
}
/**
 * Creates news View
 * @param newsMessage json news message
 * @returns wpapped news view screen
 */
function formNewsView(newsMessage) {

    var wrapper = $("<div>").addClass("news-view");
    
	var timestamp = moment.unix(newsMessage.modificationDate / 1000);
	var date = timestamp.format('LL');

    var newsDate = $("<div>").addClass("news-message-date").text(date);
    var newsTitle = $("<div>").attr("id", "title").text(newsMessage.title);
    var newsBrief = $("<div>").attr("id", "brief").text(newsMessage.brief);
    var newsContent = $("<div>").attr("id", "content-news").text(newsMessage.content);
    
	var tags = getTagList(newsMessage.id);
	
	var newsTags = $("<div>").attr("id", "news-tags").text(tags);

    wrapper.append(newsDate);
    wrapper.append(newsTitle);
    wrapper.append(newsBrief);
    wrapper.append(newsContent);
    wrapper.append(newsTags);

    return wrapper;
}
/**
 * Creates news tag list
 * @param newsId news id
 * @returns string of news tags
 */
function getTagList(newsId) {
	
	var tagList = "";
	
	$.ajax({
	    url: '/News/news/' + newsId + '/tags/',
	    type: 'GET',
	    async: false,
	    success: function(tags) {
	    	
	    	$(tags).each(function() {
	    		tagList = tagList + " " + this.tagName;
			});
	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
	
	return tagList;
}
/**
 * Loads all existing tags and creates tags select menu
 * @returns tags select menu
 */
function formTagList() {

	var result = "";
	
	$.ajax({
	    url: '/News/tags/',
	    type: 'GET',
	    async: false,
	    success: function(tags) {
	    	
	    	var selectMenu = $("<div>").addClass("select-menu");  	
	    	var title = $("<div>").attr("id", "tags-menu-title").text(jQuery.i18n.prop('label.tags'));
	    	
	    	selectMenu.append(title);
	    	
	    	var tagList = $("<select multiple>").attr("id", "newsTags").attr("size", 8);
	    	
	    	$(tags).each(function() {
	    		var option = $("<option>").text(this.tagName).attr("value", this.tagName);
	    		tagList.append(option);
	    	});
	    	
	    	selectMenu.append(tagList);
	    	result = selectMenu;
	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
	
	return result;
}
/**
 * Loads all existing authors and creates authors select menu
 * @returns authors select menu
 */
function formAuthorsList() {
	
	var result = "";
	
	$.ajax({
	    url: '/News/authors/',
	    type: 'GET',
	    async: false,
	    success: function(authors) {
	    	
	    	var selectMenu = $("<div>").addClass("select-menu");
	    	
	    	var title = $("<div>").attr("id", "authors-menu-title").text(jQuery.i18n.prop('label.authors'));
	    	selectMenu.append(title);
	    	
	    	var authorsList = $("<select multiple>").attr("id", "newsAuthors").attr("size", 8);
	    	
	    	$(authors).each(function() {
	    		var option = $("<option>").text(this.name).attr("value", this.id);
	    		authorsList.append(option);
	    	});
	    	
	    	selectMenu.append(authorsList);
	    	result = selectMenu;

	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
	
	return result;
}

/**
 * If called without id, places empty form, otherwise gets news message by id
 */
function showAddingForm(newsId) {
	
	$('#menu').contents().not('.nav-menu').remove();
	$('#content').empty();
	
	if (newsId) {

		$.getJSON("/News/news/" + newsId, function(newsMessage) {

		var addingForm = createAddingForm(newsMessage.id, newsMessage.title, newsMessage.brief, newsMessage.content);
		var editButtons = createEditTableButtons(newsMessage.id);
		var tagsMenu = createEditTagsMenu(newsMessage.id);
		var authorsMenu = createEditAuthorsMenu(newsMessage.id);

		$('#content').append(addingForm);
		$('#content').append(editButtons);	
		
		$('#nav-menu-links').empty();
		$('#nav-menu-links').append(createViewNewsHref());	
		$("#nav-menu-links").append(createBackHref());
		
		$("#menu").append(tagsMenu);
		$("#menu").append(authorsMenu);
		$("#menu").append(createViewCommentsHref(newsMessage.id));
	
		});
	} else {
		
		var addingForm = createAddingForm();
		var editButtons = createEditTableButtons();
		
		$('#content').append(addingForm);
		$('#content').append(editButtons);
		
		$('#nav-menu-links').empty();
		$('#nav-menu-links').append(createViewNewsHref());
		
    	$("#menu").append(createEditTagsMenu());
    	$("#menu").append(createEditAuthorsMenu());		
		$("#nav-menu-links").append(createBackHref());

	}
}
/**
 * Load news tags and creates edit tags menu
 * @param id news message id
 * @returns edit tags menu
 */
function createEditTagsMenu(id){
	
	var tagsMenu = $("<div>").addClass("tags-menu");
	var title = $("<div>").attr("id", "tags-menu-title").text(jQuery.i18n.prop('label.tags'));	
	var tagList = $("<ul>");
	tagsMenu.append(title);
	
	if (id) {

		$.ajax({
		    url: '/News/news/' + id + '/tags/',
		    type: 'GET',
		    async: false,
		    success: function(tags) {
		    		    	
		    	$(tags).each(function() {	    		
		    		var listItem =$("<li>").text(this.tagName).click(function() {
						$(this).remove();
					});
		    		tagList.append(listItem);
		    	});
		    },
		    error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
		
	} 
	
	var addNewsTagLink = $("<a>").attr("href", "#").text(jQuery.i18n.prop('label.add')).click(function() {
		showTagsModal();
		$(".tags-menu > p").remove();
	});
	
	tagsMenu.append(tagList);
	tagsMenu.append(addNewsTagLink);
	
	return tagsMenu;
}

/**
 * Load news authors and creates edit authors menu
 * @param id news message id
 * @returns edit authors menu
 */
function createEditAuthorsMenu(newsId) {

	var authorsMenu = $("<div>").addClass("authors-menu");
	var title = $("<div>").attr("id", "authors-menu-title").text(jQuery.i18n.prop('label.authors'));	
	
	authorsMenu.append(title);
	
	var authorsList = $("<ul>");
	
	if (newsId) {
		$.ajax({
		    url: '/News/news/' + newsId + '/authors/',
		    type: 'GET',
		    success: function(authors) {
		    	
		    	$(authors).each(function() {
		    		
		    		var listItem =$("<li>").attr("value", this.id).text(this.name)
		    		.click(function() {
						$(this).remove();
					});
		    		authorsList.append(listItem);
		    	});
	
		    },
		    error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	}
	
	var addAuthorLink = $("<a>").attr("href", "#").text(jQuery.i18n.prop('label.add')).click(function() {
		showAuthorsModal();
		$(".authors-menu > p").remove();
	});
	
	authorsMenu.append(authorsList);
	authorsMenu.append(addAuthorLink);
	
	return authorsMenu;
}
/**
 * Creates news mesage object
 * @param title
 * @param brief
 * @param content
 * @param tags
 * @param authors
 * @returns news message object
 */
function createNewsMessage(title, brief, content, tags, authors) {
	
	var message = new Object();
	message.title = title;
	message.brief = brief;
	message.content = content;
	message.tags = tags;
	message.authors = authors;

	return message;
}
/**
 * Creates form for edit\add news message.
 * If called without parameters, creates empty form
 * @param id
 * @param title
 * @param brief
 * @param content
 * @returns news adding form 
 */
function createAddingForm(id, title, brief, content) {
	
	id = id || "";
	title = title || "";
	brief = brief || "";
	content = content || "";

    var editTable = $("<table>").addClass("edit-news-table");

    var titleRow = $("<tr>");
    var labelCol = $("<td>").addClass("table-header").text(jQuery.i18n.prop('label.title'));
    var titleCol = $("<td>").addClass("table-content");
    var titleTextarea = $("<textarea>").attr("id", "news-title");
    titleTextarea.attr("rows", "5");
    titleTextarea.attr("cols, 50");
    titleTextarea.text(title);

    titleTextarea.appendTo(titleCol);
    labelCol.appendTo(titleRow);
    titleCol.appendTo(titleRow);

    var briefRow = $("<tr>");
    var labelCol = $("<td>").addClass("table-header").text(jQuery.i18n.prop('label.brief'));
    var briefCol = $("<td>").addClass("table-content");
    var briefTextarea = $("<textarea>").attr("id", "news-brief");
    briefTextarea.attr("rows", "8");
    briefTextarea.attr("cols, 50");
    briefTextarea.text(brief);

    briefTextarea.appendTo(briefCol);
    labelCol.appendTo(briefRow);
    briefCol.appendTo(briefRow);

    var contentRow = $("<tr>");
    var labelCol = $("<td>").addClass("table-header").text(jQuery.i18n.prop('label.content'));
    var contentCol = $("<td>").addClass("table-content");
    var contentTextarea = $("<textarea>").attr("id", "news-content");
    contentTextarea.attr("rows", "22");
    contentTextarea.attr("cols, 50");
    contentTextarea.text(content);

    contentTextarea.appendTo(contentCol);
    labelCol.appendTo(contentRow);
    contentCol.appendTo(contentRow);
    
    editTable.append(titleRow);
    editTable.append(briefRow);
    editTable.append(contentRow);
    
	return editTable;
}
/**
 * Creates buttons for adding form
 */
function createEditTableButtons(id) {

	var wrapper = $("<div>").addClass("table-buttons-wrapper");
	var editBtn = $("<button>").addClass("table-edit-btn").text(jQuery.i18n.prop('label.save'));

	if (id){
		editBtn.click(function() {
			updateNews(id);
		});
	} else {
		editBtn.click(function() {
			saveNews();
		});
	}
	
	var cancelBtn = $("<button>").addClass("table-edit-btn").text(jQuery.i18n.prop('label.cancel'));
	cancelBtn.click(function() {
		placeAll(_pageConfig.newsPerPage, 0);
	});
	
	wrapper.append(editBtn);
	wrapper.append(cancelBtn);
	
	return wrapper;
}

/**
 * Creates view for news comments
 */
function formNewsCommentsView(newsId) {

    var commentsWrapper = $("<div>").attr("id", "comments-wrapper");	
    var commentsArea = $("<div>").addClass("comments-area");
    var textArea = $("<textarea>").attr("id", "comment-area");
    var addButton = $("<button>").addClass("comment-button").text(jQuery.i18n.prop('label.addComment'));
    
    addButton.click(function() {
    	
    	var text = $("#comment-area").val();
    	addComment(newsId, text);
    });
    
    commentsArea.append(textArea);
    commentsArea.append(addButton);   
    commentsArea.append(commentsWrapper);
    
    return commentsArea;
}
/**
 * Add comment to news message
 * @param newsId news message id
 * @param text comment text
 */
function addComment(newsId, text) {
	
	var comment = {			
	        "text": text,
	};

	if (validateComment(comment)) {
	
		$.ajax({
			url : "/News/news/" + newsId + "/comments/",
			type : 'POST',
		    data: JSON.stringify(comment),
		    datatype : "json",
		    contentType: "application/json; charset=utf-8",
			success : function(result) {  
					createCommentsPaging(newsId, _pageConfig.commentsPerPage, 0);
			},
			error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	}
}
/**
 * Creates menu for news navigation
 */
function createNewsGroupMenu() {
	var slider = $("<div>").addClass("slider");
	
	var slideFunc = function() {

		$(this).next("div.slider-content").slideToggle("slow")
        .siblings("div.slider-content:visible").slideUp("slow");
        $(this).toggleClass("active");
        $(this).siblings("div.slider-header").removeClass("active");
	};
	
	var tagsSlider = $("<div>").addClass("slider-header").text(jQuery.i18n.prop('label.tags'))
	.click(function() {
		loadTagsCount();
		slideFunc();
	});
	var tagsContent = $("<div>").addClass("slider-content").attr("id", "tags-content").hide();
	
	var authorsSlider = $("<div>").addClass("slider-header").text(jQuery.i18n.prop('label.authors'))
	.click(function() {
		loadAuthorsCount();
		slideFunc();
	});
	
	var authorsContent = $("<div>").addClass("slider-content").attr("id", "authors-content").hide();;
	
	var commentsSlider = $("<div>").addClass("slider-header").text(jQuery.i18n.prop('label.comments'))
	.click(function() {
		slideFunc();
	});
	
	var commentsContent = $("<div>").addClass("slider-content").attr("id", "comments-content").hide();
	
	var commentSliderList = $("<ul>");
	var items = $("<li>");
		
	items.append(createViewTopCommentedLink(5));
	items.append(createViewTopCommentedLink(20));
	items.append(createViewTopCommentedLink(100));

	commentSliderList.append(items);
	commentsContent.append(commentSliderList);
	
	slider.append(tagsSlider);
	slider.append(tagsContent);
	slider.append(authorsSlider);
	slider.append(authorsContent);
	slider.append(commentsSlider);
	slider.append(commentsContent);
	
	$("#menu").append(slider);

	$(".slider div.slider-header").click(function(){
	     
	  $(this).next("div.slider-content").slideToggle("slow")
	  .siblings("div.slider-content:visible").slideUp("slow");
	  $(this).toggleClass("active");
	  $(this).siblings("div.slider-header").removeClass("active");
	});
}

function loadMostCommentedNews(total, amount, skip) {
	_pageConfig.loadNewsURL = "/News/news/commented?total=" + total + "&";
	placeNews(amount, skip);
}
/**
 * Load tags list with news count
 */
function loadTagsCount() {
	
	$.ajax({
	    url: '/News/tags/list',
	    type: 'GET',
	    async: false,
	    success: function(data) {
	    	
	    	var list = $("<ul>");
	    	var tags = Object.keys(data);
	    	
	    	for (var i = 0; i < tags.length; i++) {

	    		var li = $("<li>");
    			var a = $("<a>").text(tags[i] + " ( " + data[tags[i]] + " )")
    			  	.attr("href", "#").attr("onclick", "loadNewsByTag('" + tags[i] + "')");
    			 li.append(a);
    			 list.append(li);
	    	}
			
	    	$("#tags-content").empty();
	    	$("#tags-content").append(list);
	 
	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
}

function loadNewsByTag(tag) {
		_pageConfig.currentNewsPage = 0;
		 _pageConfig.loadNewsURL = "/News/news/?tag=" + tag + "&";
		 placeNews(_pageConfig.newsPerPage, 0);
}

/**
 * Load tags list with news count
 */
function loadAuthorsCount() {
	
	$.ajax({
	    url: '/News/authors/list',
	    type: 'GET',
	    async: false,
	    success: function(data) {
	    	
	    	var list = $("<ul>");
	    	var authors = Object.keys(data);
	    	
	    	for (var i = 0; i < authors.length; i++) {

	    		var li = $("<li>");
    			var a = $("<a>").text(authors[i] + " ( " + data[authors[i]] + " )")
    			  	.attr("href", "#").attr("onclick", "loadNewsByAuthor('" + authors[i] + "')");
    			 li.append(a);
    			 list.append(li);
	    	}
	    		    	
	    	$("#authors-content").empty();
	    	$("#authors-content").append(list);
	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
}

function loadNewsByAuthor(author) {
	_pageConfig.currentNewsPage = 0;
	 _pageConfig.loadNewsURL = "/News/news/?author=" + author + "&";
	 placeNews(_pageConfig.newsPerPage, 0);
}

/**
 * Creates form for editing news comments
 * @param newsId news message
 */
function createEditCommentsPage(newsId) {
	
	$("#content").empty();
	var wrapper = $("<div>").addClass("extended-comments-view ");
	var area = $("<div>").addClass("paging-area").attr("id", "comment-paging");
	
	var result = getPagedComments(newsId, _pageConfig.extendedCommentsPerPage, _pageConfig.extendedCommentsPage * _pageConfig.extendedCommentsPerPage);

	$(result.comments).each(function() {  
		
		  var comment = $("<div>").addClass("comment");
		  var timestamp = moment.unix(this.creationDate / 1000);
		  var date = timestamp.format('DD/MM/YYYY, h:mm:ss');
	      var commentDate = $("<div>").addClass("comment-date").text(date);
	      var commentText = $("<div>").addClass("comment-text").text(this.text);
	      var editLinsk = $("<div>").addClass("news-comment-links");
	      var commentId = this.id;
	      
	      var deleteLink = $("<a>").attr("href", "#").text(jQuery.i18n.prop('label.delete')).click(function() {
	    	  deleteCommentById(commentId);
	    	  createEditCommentsPage(newsId);
			});
	      
	      editLinsk.append(deleteLink);
	      
	      comment.append(commentDate);
	      comment.append(commentText);
	      comment.append(editLinsk);
	      
	      wrapper.append(comment);   				
	});
	
	$(area).pagination({
        items: result.total,
    	currentPage: _pageConfig.extendedCommentsPage + 1,
        itemsOnPage: _pageConfig.extendedCommentsPerPage,
        displayedPages: 3,
        edges: 1,
        onPageClick : function(pageNumber) {
        	_pageConfig.extendedCommentsPage = pageNumber-1;
        	createEditCommentsPage(newsId);
		},
        cssStyle: 'light-theme'
    });
	
	$("#content").append(wrapper);
	$("#content").append(area);
}

/**
 * Delete comment by id  
 * @param commentId comment id
 */
function deleteCommentById(commentId) {

	if (confirm(jQuery.i18n.prop('msg.confirmDeleteComment'))) {

		$.ajax({
			url : '/News/comments/' + commentId,
			type : 'DELETE',
			async : false,
			error : function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	}
}