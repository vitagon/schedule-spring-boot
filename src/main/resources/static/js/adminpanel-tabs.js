$(document).click(function(event) {
	
	// when user clicks on 'content' on mobile devices, then hide sidemenu if it is opened
	if (!$(event.target).closest("#admin-panel_tabs-wrap").length &&
			$(window).width() < 768 &&
			$('#admin-panel_tabs-wrap').hasClass('side-menu-toggled') &&
			$('#admin-panel_tabs-content').hasClass('side-menu-toggled')) {
		$('#admin-panel_tabs-wrap').toggleClass('side-menu-toggled');
		$('#admin-panel_tabs-content').toggleClass('side-menu-toggled');
	}
});

// jQuery script for sidebar
$('#sidebar-toggle').click(function(e) {
	$('#admin-panel_tabs-wrap').toggleClass('side-menu-toggled');
	$('#admin-panel_tabs-content').toggleClass('side-menu-toggled');
	e.stopPropagation();
});

$('#admin-panel_tabs a').on('click', function(e) {
  e.preventDefault();
  let $target = $(e.target);
  
  /*
   * 1. Когда нажата кнопка показа/скрытия аккордеона
   */
  if ($target.closest('li').hasClass('nav-item_lvl-0') && $target.hasClass('submenu_parent-link')) {
	  
	  // проверить открыт аккордеон или нет
	  let isExpanded = $target.attr('aria-expanded');
	  
	  if (isExpanded === 'true') {
		  $('.submenu_parent-link').attr('aria-expanded', 'false');
		  $('.submenu_parent-link').siblings('ul').collapse('hide');
	  } else {
		  // закрыть все аккордеоны, кроме выбранного
		  let submenuParentLinks = $('.submenu_parent-link');
		  for (let submenuParentLink of submenuParentLinks) {
			  if (submenuParentLink !== e.target) {
				  let $submenuParentLink = $(submenuParentLink);
				  let ariaControls = $submenuParentLink.attr('aria-controls');
				  $submenuParentLink.siblings('ul').collapse('hide');
			  }
		  }
		  
		  // убрать темный фон с кнопок, который означает, что аккордеон открыт
		  $('.submenu_parent-link').attr('aria-expanded', 'false');
		  
		  // добавить темный фон для нажатой кнопки
		  $target.attr('aria-expanded', 'true');
		  // показать аккордеон
		  $target.siblings('ul').collapse('show');
	  }
	  
  }
  
  /*
   * 2. Когда нажата ссылка на вкладку
   */
  if ($target.closest('ul').hasClass('collapse')) {
	  // удаляем класс .active со всех активных ранее ссылок (убираем фон)
	  $('.nav-item_lvl-0 .active').removeClass('active');
	  
	  // ОЧЕНЬ ВАЖНА последовательность этих двух ниже строк
	  // 1. Показываем контент выбранной вкладки
	  // 2. А затем, корневую ссылку, управляющую аккордеоном, помечаем как активную
	  // Если поменять строки местами, то метод tab() удалит класс active.
	  $target.tab('show');
	  $target.closest('ul').siblings('a').addClass('active');
  }
  
  /*
   * 3. Когда нажата ссылка, находящаяся на корневом уровне, у которой нет выпадающего списка
   */
  if ($target.closest('li').hasClass('nav-item_lvl-0') && !$target.hasClass('submenu_parent-link')) {
	  // закрываем все аккордеоны
	  $('.submenu_parent-link').siblings('ul').collapse('hide');
	  // помечаем все корневые ссылки, имеющие аккордеоны, чтобы они знали, что их аккордеон закрыт 
	  $('.submenu_parent-link').attr('aria-expanded', 'false');
	  // удаляем класс .active со всех активных ранее ссылок (убираем фон)
	  $('.nav-item_lvl-0 .active').removeClass('active');
	  
	  // показываем контент, соответствующий данной вкладке
	  $target.tab('show');
  }
  
  // if it is mobile device, then hide side menu (after user had chosen tab)
  if ($(window).width() < 768) {
	  $('#admin-panel_tabs-wrap').toggleClass('side-menu-toggled');
	  $('#admin-panel_tabs-content').toggleClass('side-menu-toggled');
  }
});