
CREATE TABLE IF NOT EXISTS `#__hdflvaddgoogle` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `code` text NOT NULL,
  `showoption` tinyint(1) NOT NULL,
  `closeadd` int(6) NOT NULL,
  `reopenadd` tinytext NOT NULL,
  `publish` int(1) NOT NULL,
  `ropen` int(6) NOT NULL,
  `showaddc` tinyint(1) NOT NULL,
  `showaddm` tinytext NOT NULL,
  `showaddp` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

INSERT INTO `#__hdflvaddgoogle` (`id`, `code`, `showoption`, `closeadd`, `reopenadd`, `publish`, `ropen`, `showaddc`, `showaddm`, `showaddp`) VALUES
    (1, '', 1, 5, '0', 0, 5, 0, '0', '0');

CREATE TABLE IF NOT EXISTS `#__hdflvplayerads` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `published` tinyint(4) NOT NULL,
  `adsname` varchar(255) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  `postvideopath` varchar(255) NOT NULL,
  `home` int(11) NOT NULL,
  `targeturl` varchar(255) NOT NULL,
  `clickurl` varchar(255) NOT NULL,
  `impressionurl` varchar(255) NOT NULL,
  `impressioncounts` int(11) NOT NULL DEFAULT '0',
  `clickcounts` int(11) NOT NULL DEFAULT '0',
  `adsdesc` varchar(500) NOT NULL,
  `typeofadd` varchar(50) NOT NULL,
  `imaaddet` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `#__hdflvplayerlanguage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_lang` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


INSERT INTO `#__hdflvplayerlanguage` (`id`, `player_lang`) VALUES
(1, 'a:50:{s:5:"pause";s:5:"Pause";s:4:"play";s:4:"Play";s:6:"replay";s:6:"Replay";s:13:"changequality";s:14:"Change Quality";s:4:"zoom";s:4:"Zoom";s:6:"zoomin";s:7:"Zoom In";s:7:"zoomout";s:8:"Zoom out";s:5:"share";s:5:"Share";s:10:"fullscreen";s:10:"Fullscreen";s:14:"exitfullScreen";s:16:"Exit Full Screen";s:12:"playlisthide";s:19:"Hide Related Videos";s:12:"playlistview";s:19:"Show Related Videos";s:12:"sharetheword";s:16:"Share This Video";s:11:"sendanemail";s:13:"Send an email";s:5:"email";s:5:"Email";s:2:"to";s:8:"To Email";s:4:"from";s:10:"From Email";s:4:"note";s:7:"Message";s:4:"send";s:4:"Send";s:4:"copy";s:4:"Copy";s:8:"copylink";s:4:"link";s:9:"copyembed";s:5:"Embed";s:6:"social";s:6:"Social";s:7:"quality";s:7:"Quality";s:8:"facebook";s:17:"Share on Facebook";s:10:"googleplus";s:16:"Share on Google+";s:6:"tumblr";s:15:"Share on Tumblr";s:5:"tweet";s:16:"Share on Twitter";s:23:"turnoffplaylistautoplay";s:26:"Turn Off Playlist Autoplay";s:22:"turnonplaylistautoplay";s:25:"Turn On Playlist Autoplay";s:11:"adindicator";s:61:"Your selection will follow this sponsors message in - seconds";s:7:"skipadd";s:19:"Skip this ad now >>";s:9:"skipvideo";s:24:"You can skip to video in";s:8:"download";s:8:"Download";s:6:"volume";s:6:"Volume";s:3:"mid";s:3:"mid";s:11:"nothumbnail";s:22:"No Thumbnail Available";s:4:"live";s:4:"LIVE";s:18:"fillrequiredfields";s:35:"Please fill in all required fields.";s:10:"wrongemail";s:30:"Missing field Or Invalid email";s:9:"emailwait";s:6:"Wait..";s:9:"emailsent";s:31:"Thank you! Video has been sent.";s:14:"notallow_embed";s:68:"The requested video does not allow playback in the embedded players.";s:18:"youtube_ID_Invalid";s:94:"The video ID that does not have 11 characters, or if the video ID contains invalid characters.";s:24:"video_Removed_or_private";s:127:"The requested video is not found. This occurs when a video has been removed (for any reason), or it has been marked as private.";s:27:"streaming_connection_failed";s:46:"Requested streaming provider connection failed";s:15:"audio_not_found";s:49:"The requested audio is not found or access denied";s:19:"video_access_denied";s:49:"The requested video is not found or access denied";s:20:"FileStructureInvalid";s:138:"Flash Player detects an invalid file structure and will not try to play this type of file. Supported by Flash Player 9 Update 3 and later.";s:21:"NoSupportedTrackFound";s:155:"Flash Player does not detect any supported tracks (video, audio or data) and will not try to play the file. Supported by Flash Player 9 Update 3 and later.";}');

CREATE TABLE IF NOT EXISTS `#__hdflvplayername` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `published` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

INSERT INTO `#__hdflvplayername` (`id`, `name`, `published`)
            VALUES (1,'Uncategorized','1');


CREATE TABLE IF NOT EXISTS `#__hdflvplayersettings` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `published` tinyint(4) NOT NULL,
  `uploadmaxsize` int(10) NOT NULL,
  `logopath` varchar(255) NOT NULL,
  `player_colors` longtext NOT NULL,
  `player_icons` longtext NOT NULL,
  `player_values` longtext NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

INSERT INTO `#__hdflvplayersettings` (`id`, `published`, `uploadmaxsize`,`logopath`, `player_colors`, `player_icons`, `player_values`) VALUES
(1, 1, 100, '','','a:27:{s:8:"autoplay";s:1:"1";s:17:"playlist_autoplay";s:1:"1";s:13:"playlist_open";s:1:"0";s:13:"skin_autohide";s:1:"1";s:10:"fullscreen";s:1:"1";s:4:"zoom";s:1:"1";s:5:"timer";s:1:"1";s:8:"shareurl";s:1:"1";s:5:"email";s:1:"1";s:13:"volumevisible";s:1:"1";s:11:"progressbar";s:1:"1";s:9:"hddefault";s:1:"1";s:12:"imageDefault";s:1:"1";s:8:"download";s:1:"1";s:10:"prerollads";s:1:"0";s:11:"postrollads";s:1:"0";s:6:"imaAds";s:1:"1";s:7:"adsSkip";s:1:"0";s:10:"midrollads";s:1:"0";s:11:"midadrotate";s:1:"0";s:9:"midrandom";s:1:"0";s:14:"title_ovisible";s:1:"1";s:20:"description_ovisible";s:1:"1";s:7:"showTag";s:1:"1";s:14:"viewed_visible";s:1:"1";s:17:"embedcode_visible";s:1:"1";s:17:"playlist_dvisible";s:1:"0";}','a:20:{s:6:"buffer";s:1:"3";s:5:"width";s:3:"700";s:6:"height";s:3:"400";s:11:"normalscale";s:1:"2";s:15:"fullscreenscale";s:1:"2";s:6:"volume";s:2:"50";s:10:"ffmpegpath";s:15:"/usr/bin/ffmpeg";s:10:"stagecolor";s:0:"";s:10:"licensekey";s:0:"";s:7:"logourl";s:0:"";s:9:"logoalpha";s:3:"100";s:9:"logoalign";s:2:"BL";s:15:"adsSkipDuration";s:1:"5";s:17:"googleanalyticsID";s:0:"";s:8:"midbegin";s:0:"";s:11:"midinterval";s:0:"";s:14:"related_videos";s:1:"1";s:16:"relatedVideoView";s:6:"center";s:8:"nrelated";s:1:"8";s:7:"urllink";s:0:"";}');

CREATE TABLE IF NOT EXISTS `#__hdflvplayerupload` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `published` tinyint(1) NOT NULL,
  `title` varchar(100) NOT NULL,
  `times_viewed` int(11) NOT NULL,
  `filepath` varchar(10) NOT NULL,
  `videourl` varchar(255) NOT NULL,
  `thumburl` varchar(255) NOT NULL,
  `previewurl` varchar(255) NOT NULL,
  `hdurl` varchar(255) NOT NULL,
  `home` int(11) NOT NULL,
  `playlistid` int(11) NOT NULL,
  `duration` varchar(20) NOT NULL,
  `ordering` int(11) NOT NULL,
  `streamerpath` varchar(255) NOT NULL,
  `streameroption` varchar(255) NOT NULL,
  `postrollads` tinyint(4) NOT NULL,
  `prerollads` tinyint(4) NOT NULL,
  `midrollads` tinyint(4) NOT NULL,
  `imaads` tinyint(4) NOT NULL,
  `description` text NOT NULL,
  `targeturl` varchar(255) NOT NULL,
  `download` tinyint(4) NOT NULL,
  `prerollid` int(11) NOT NULL,
  `postrollid` int(11) NOT NULL,
  `access` int(11) NOT NULL,
  `islive` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

INSERT INTO `#__hdflvplayerupload`
(`id`,`published`, `title`,`times_viewed`, `filepath`, `videourl`, `thumburl`, `previewurl`, `hdurl`, `home`, `playlistid`, `duration`, `ordering`, `streamerpath`, `streameroption`, `postrollads`, `prerollads`, `description`, `targeturl`, `download`, `prerollid`, `postrollid` ,`access`) VALUES
(1, 1, 'Avatar Movie Trailer [HD]', 0 , 'Youtube', 'http://www.youtube.com/watch?v=d1_JBMrrYw8', 'http://img.youtube.com/vi/d1_JBMrrYw8/1.jpg', 'http://img.youtube.com/vi/d1_JBMrrYw8/0.jpg', '', 0 ,'1',9, '', 0, '', '', 0, 'Avatar Movie Trailer [HD]', '', '', 0, 0, 0),
(2, 1, 'HD: Super Slo-mo Surfer! - South Pacific - BBC Two',0, 'Youtube', 'http://www.youtube.com/watch?v=7BOhDaJH0m4', 'http://img.youtube.com/vi/7BOhDaJH0m4/1.jpg', 'http://img.youtube.com/vi/7BOhDaJH0m4/0.jpg', '', 0, '1', 14, '', 0, '', '', 0, '', '', '', 0, 0, 0),
(3, 1, 'Fatehpur Sikri, Taj Mahal - India (in HD)',0, 'Youtube', 'http://www.youtube.com/watch?v=UNWROFjIwvQ', 'http://img.youtube.com/vi/UNWROFjIwvQ/1.jpg', 'http://img.youtube.com/vi/UNWROFjIwvQ/0.jpg', '', 0, '1', 5, '', 0, '', '', 0, '', '', '', 0, 0, 0);
