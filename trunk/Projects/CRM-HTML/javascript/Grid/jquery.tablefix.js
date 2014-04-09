/*
 * jQuery TableFix plugin ver 1.0.1
 * Copyright (c) 2010 Otchy
 * This source file is subject to the MIT license.
 * http://www.otchy.net
 */
(function($){
	$.fn.tablefix = function(options) {
		return this.each(function(index){
			// �p�������̔���
			var opts = $.extend({}, options);
			var baseTable = $(this);
			var withWidth = (opts.width > 0);
			var withHeight = (opts.height > 0);
			if (withWidth) {
				withWidth = (opts.width < baseTable.width());
			} else {
				opts.width = baseTable.width();
			}
			if (withHeight) {
				withHeight = (opts.height < baseTable.height());
			} else {
				opts.height = baseTable.height();
			}
			if (withWidth || withHeight) {
				if (withWidth && withHeight) {
					opts.width -= 40;
					opts.height -= 40;
				} else if (withWidth) {
					opts.width -= 20;
				} else {
					opts.height -= 20;
				}
			} else {
				return;
			}
			// �O��<div>�̐ݒ�
			baseTable.wrap("<div></div>");
			var div = baseTable.parent();
			div.css({position: "relative"});
			// �X�N���[�����I�t�Z�b�g�̎擾
			var fixRows = (opts.fixRows > 0) ? opts.fixRows : 0;
			var fixCols = (opts.fixCols > 0) ? opts.fixCols : 0;
			var offsetX = 0;
			var offsetY = 0;
			baseTable.find('tr').each(function(indexY) {
				$(this).find('td,th').each(function(indexX){
					if (indexY == fixRows && indexX == fixCols) {
						var cell = $(this);
						offsetX = cell.position().left;
						offsetY = cell.parent('tr').position().top;
						return false;
					}
				});
				if (indexY == fixRows) {
					return false;
				}
			});
			// �e�[�u���̕����Ə�����
			var crossTable = baseTable.wrap('<div></div>');
			var rowTable = baseTable.clone().wrap('<div></div>');
			var colTable = baseTable.clone().wrap('<div></div>');
			var bodyTable = baseTable.clone().wrap('<div></div>');
			var crossDiv = crossTable.parent().css({position: "absolute", overflow: "hidden"});
			var rowDiv = rowTable.parent().css({position: "absolute", overflow: "hidden"});
			var colDiv = colTable.parent().css({position: "absolute", overflow: "hidden"});
			var bodyDiv = bodyTable.parent().css({position: "absolute", overflow: "auto"});
			div.append(rowDiv).append(colDiv).append(bodyDiv);
			// �N���b�v�̈�̐ݒ�
			var bodyWidth = opts.width - offsetX;
			var bodyHeight = opts.height - offsetY;

			// t.miyagi ���X�N���[�������ہA�\������錻�ۂ��C�� BGN
			//�X�N���[�����̎擾
			var scrollbarInner = document.createElement('p');

			var scrollbarOuter = document.createElement('div');
			scrollbarOuter.style.position = "absolute";
			scrollbarOuter.style.top = "0px";
			scrollbarOuter.style.left = "0px";
			scrollbarOuter.style.visibility = "hidden";
			scrollbarOuter.appendChild (scrollbarInner);

			scrollbarInner.style.width = "100%";
			scrollbarInner.style.height = "200px";
			scrollbarOuter.style.width = "200px";
			scrollbarOuter.style.height = "150px";
			scrollbarOuter.style.overflow = "hidden";
			document.body.appendChild (scrollbarOuter);
			var w1 = scrollbarInner.offsetWidth;
			scrollbarOuter.style.overflow = 'scroll';
			var w2 = scrollbarInner.offsetWidth;
			if (w1 == w2) w2 = scrollbarOuter.clientWidth;
			document.body.removeChild (scrollbarOuter);
			var scrollBarWidth = (w1 - w2);
			// t.miyagi ���X�N���[�������ہA�\������錻�ۂ��C�� END

			crossDiv.width(offsetX).height(offsetY);
			rowDiv
				.width(bodyWidth + (withWidth ? 20 : 0) + (withHeight ? 20 : 0))
				.height(offsetY)
				.css({left: offsetX + 'px'});
			rowTable.css({
				marginLeft: -offsetX + 'px',
				marginRight: (withWidth ? 20 : 0) + (withHeight ? 20 : 0) + 'px'
			});
			colDiv
				.width(offsetX)
				.height(bodyHeight + (withWidth ? 20 : 0) + (withHeight ? 20 : 0))
				.css({top: offsetY + 'px'});
			colTable.css({
				marginTop: -offsetY + 'px',
				marginBottom: (withWidth ? 20 : 0) + (withHeight ? 20 : 0) + 'px'
			});
			var extraWidth = 0;		
			if (withWidth && withHeight) {
					extraWidth = 16.1;
			} 
			bodyDiv
				.width(bodyWidth + (withWidth ? 20 : 0) + (withHeight ? 20 : 0) + extraWidth)
				.height(bodyHeight + (withWidth ? 20 : 0) + (withHeight ? 20 : 0))
				.css({left: offsetX + 'px', top: offsetY + 'px'});
				
			bodyTable.css({
				marginLeft: -offsetX + 'px',
				marginTop: -offsetY + 'px',
				marginRight: (withWidth ? 20 : 0) + 'px'
				//marginBottom: (withHeight ? 20 : 0) + 'px'
			});
						
			if (withHeight) {
				rowTable.width(bodyTable.width());
			}
			// �E�X�E�N�E��E��E�[�E��E��E�A�E��E�
			bodyDiv.scroll(function() {
				rowDiv.scrollLeft(bodyDiv.scrollLeft());
				colDiv.scrollTop(bodyDiv.scrollTop());
			});
			// �E�O�E��E� div �E�̐ݒ�
			div
				.width(opts.width + (withWidth ? 20 : 0) + (withHeight ? 20 : 0))
				.height(opts.height + (withWidth ? 20 : 0) + (withHeight ? 20 : 0));
		});
	}
})(jQuery);