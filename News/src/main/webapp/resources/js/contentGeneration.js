/**
 * 
 */

var nameList = [
	"Avelina  Swabey",
	"Avalon  Littlefield", 
	"Lalage  Kunkle", 
	"Philipa  Filby", 
	"Lake  Shallenberger", 
	"Neal  Osterweis",
	"Thurstan  Flickinger", 
	"Magnolia  Courtney", 
	"Minnie  Weeter", 
	"Conor  Seelig", 
	"Kenelm  Duncan",
	"Serina  Hair", 
	"Ferdinanda  Chappel", 
	"Lyndsay  Wolff", 
	"Misty  Spring",
	"Prue  Rowe", 
	"Rowena  Mench", 
	"Cullen  Cowher", 
	"Bruce  Stanfield",
	"Harvie  Lester",
	"Rachelle  Newbiggin",
	"Janna  Lucy", 
	"Ellis  Robinson", 
	"Indy  Light", 
	"Ebenezer  Kepple", 
	"Pleasance  Drabble", 
	"Ceara  Wheeler", 
	"Lexi  Blackburn", 
	"Everard  Boyd", 
	"Cass  Mitchell", 
	"Holly  Pratt", 
	"Mavis  Cram", 
	"Humbert  Eva", 
	"Tansy  Keister", 
	"Augusta  Roadman", 
	"Femie  Reighner", 
	"Shauna  Atweeke", 
	"Don  Conkle", 
	"Joshua  Jackson", 
	"Panda  Philbrick", 
	"Jake  Bard", 
	"Eric  Craig", 
	"Egbert  Edwards", 
	"Bobby  Rockwell",
	"Leanora  Fraser", 
	"Nolan  Sauter", 
	"Tony  Reade", 
	"Clemency  Barrett", 
	"Audra  Howard",
	"Grahame  Churchill",
	"Кирпа Ирина",
	"Зубков Егор",
	"Эсмонд Егоp",
	"Набокина Елена",
	"Новолодский Дмитрий",
	"Тарасова Ева",
	"Яницкий Всеволод",
	"Бормотов Артемий",
	"Дейнекина Зоя",
	"Янишин Владислав",
	"Николенко Борислав",
	"Элькина Лариса",
	"Цейдлиц Прохор",
	"Еськова Виктория",
	"Данилов Семён",
	"Бондарева Агния",
	"Островерха Вероника",
	"Дуванов Игорь",
	"Конев Рюрик",
	"Гершельмана Алина",
	"Хорошилова Ирина",
	"Жирова Александра",
	"Мишнева Надежда",
	"Воронов Павел",
	"Снегирёва Алина",
	"Кидирбаева Жанна",
	"Кайназарова Роза",
	"Тенишев Карл",
	"Чикунова Оксана",
	"Осин Серафим",
	"Перминова Елизавета",
	"Яимова Алина",
	"Подогов Максим",
	"Павленко Надежда",
	"Фирсов Роман",
	"Михалицына Яна",
	"Жуков Вадим",
	"Лещёв Сергей",
	"Кирдань Егор",
	"Богданов Владислав",
	"Кушкина Полина",
	"Мамонова Александра",
	"Беляева Анастасия",
	"Караева Ярослава",
	"Чадова Валентина",
	"Лепёхина Надежда",
	"Москвитина Алиса",
	"Кинжаев Артём",
	"Луков Леонид",
	"Рождественский Павел"
	];

function generateAuthors() {
	
	for (var i = 0; i < nameList.length; i++) {
		
		var author = {
				"name" : nameList[i]
		};
		
		saveAuthor(author);
	}
}

function loadAuthorsWithId() {
	
	var authorsList = new Array();
	
	$.ajax({
	    url: '/News/authors/',
	    type: 'GET',
	    async: false,
	    success: function(authors) {
	    	
	    	$(authors).each(function(i) {
	 
	    		authorsList[i] = {
	    				"id" : this.id,
	    				"name" : this.name
	    		};
	    	});	    	

	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
	
	var tagsList = new Array();
	
	$.ajax({
	    url: '/News/tags/',
	    type: 'GET',
	    async: false,
	    success: function(tags) {
	    	
	    	$(tags).each(function(i) {
	 
	    		tagsList[i] = {
	    				"tagName" : this.tagName
	    		};
	    	});	    	

	    },
	    error: function(jqXHR) {
			showErrorModal(jqXHR.status);
		}
	});
	
	for (var i = 1051; i < 11250; i++) {
		
		$.ajax({
		    url: '/News/news/' + i,
		    type: 'GET',
		    async: false,
		    success: function(newsMessage) {
		    	
		    	var newsTags = [];
		    	var newsAuthors =[];
		    	
		    	for (var t = 0; t < 3; t++) {
		    		newsTags[t] = tagsList[Math.floor((Math.random() * 9))];
//		    		console.log(newsTags[t]);
		    	}
		    		    	
		    	for (var a = 0; a < 3; a++) {
		    		newsAuthors[a] = authorsList[Math.floor((Math.random() * 99))];
//			    	console.log(newsAuthors[a]);
		    	}
		    	
		    	var news = createNewsMessage(newsMessage.title, newsMessage.brief, 
		    			newsMessage.content, newsTags, newsAuthors);	
		    	
		    	$.ajax({
					url : "/News/news/" + i,
					type : 'PUT',
				    data: JSON.stringify(news),
				    datatype : "json",
				    async : false,
				    contentType: "application/json; charset=utf-8",
					success : function(result) {  
						console.log("ok " + i);
					},
					error: function(jqXHR) {
						console.log("ERRRRRRRRRRRRORRRRR!");
//						showErrorModal(jqXHR.status);
					}
				});
		    	
//		    	console.log("ok " + i);

		    },
		    error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	}			
}

function generateRandom() {
	
for (var i = 1051; i < 1052; i++) {
		
		$.ajax({
		    url: '/News/news/' + i,
		    type: 'GET',
		    async: false,
		    success: function(newsMessage) {
		    	
		    	var newsTags = new Array();
		    	var newsAuthors = new Array();
		    	
		    	for (var t = 0; t < 3; t++) {
		    		newsTags[i] = tagsList[Math.floor((Math.random() * 9))];
		    	}
		    	
		    	consile.log(newsTags);
		    	
		    	for (var a = 0; a < 2; a++) {
		    		newsAuthors[i] = authorsList[Math.floor((Math.random() * 99))];
		    	}
		    	
		    	consile.log(newsAuthors);

		    },
		    error: function(jqXHR) {
				showErrorModal(jqXHR.status);
			}
		});
	}
	
}