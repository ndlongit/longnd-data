��- - a l t e r   c o l u m n   t y p e   c o l _ d a t e M a j R u b i s   t o   d a t e t i m e  
 u p d a t e   V I P . d b o . C O L _ C O L L A B O R A T E U R    
 s e t   c o l _ d a t e M a j R u b i s   =   c o n v e r t ( d a t e t i m e ,   l e f t ( c o l _ d a t e M a j R u b i s , 1 9 ) )  
 g o  
 a l t e r   t a b l e   V I P . d b o . C O L _ C O L L A B O R A T E U R  
 a l t e r   c o l u m n   c o l _ d a t e M a j R u b i s   d a t e t i m e   n u l l  
 g o  
  
 - -   d e l e t e   a l l   o l d   d a t b a s e   t e s t   o n   P E R _ P E R I M E T R E  
 d e l e t e   f r o m   V I P . d b o . P E R _ P E R I M E T R E  
 w h e r e   e n t _ i d = ' 0 1 5 '   a n d   p e r _ i d   n o t   l i k e   ' B Y E F E % '  
 g o  
  
 - -   i n s e r t   U O   t o   d b o . P T Y _ P E R I M E T R E _ T Y P E  
 i n s e r t   i n t o   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E  
 s e l e c t   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t   ( n v a r c h a r ( 3 0 ) , m a x ( p t y _ i d ) + 1 ) , 1 0 )   , ' U O ' , ' 0 1 5 ' , N U L L   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E  
 g o  
 - - i n s e r t   U O   t o   d b o . P E R _ P E R I M E T R E    
 i n s e r t   i n t o   V I P . d b o . P E R _ P E R I M E T R E ( p e r _ i d , p e r _ n a m e , p e r _ p a r e n t _ i d , e n t _ i d , p t y _ i d , l a g _ i d , e t j _ i d )  
 s e l e c t   ' B Y E F E _ U O _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d U O ) , 1 0 ) , N o m , N U L L , ' 0 1 5 ' ,  
 ( s e l e c t   p t y _ i d   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E   w h e r e   p t y _ n a m e   l i k e   ' U O '   a n d   e n t _ i d = ' 0 1 5 ' ) , 1 , 2   f r o m   H e m e r a . d b o . U O  
 g o  
 - - s e l e c t   *   f r o m   H e m e r a . d b o . S o c i e t e  
 - -   i n s e r t   S o c i e t e   t o   d b o . P E R _ P E R I M E T R E    
 i n s e r t   i n t o   V I P . d b o . P E R _ P E R I M E T R E ( p e r _ i d , p e r _ n a m e , p e r _ p a r e n t _ i d , e n t _ i d , p t y _ i d , l a g _ i d , e t j _ i d )  
 s e l e c t   ' B Y E F E _ S O C I E T E _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d S o c i e t e ) , 1 0 ) , N o m , ' B Y E F E _ U O _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d U O ) , 1 0 ) , ' 0 1 5 ' ,  
 ( s e l e c t   p t y _ i d   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E   w h e r e   p t y _ n a m e   l i k e   ' S o c i � t � '   a n d   e n t _ i d = ' 0 1 5 ' ) , 1 , 2   f r o m   H e m e r a . d b o . S o c i e t e  
 g o  
 - - s e l e c t   *   f r o m   H e m e r a . d b o . D i r e c t i o n  
 - -   i n s e r t   D i r e c t i o n   t o   d b o . P E R _ P E R I M E T R E    
 i n s e r t   i n t o   V I P . d b o . P E R _ P E R I M E T R E ( p e r _ i d , p e r _ n a m e , p e r _ p a r e n t _ i d , e n t _ i d , p t y _ i d , l a g _ i d , e t j _ i d )  
 s e l e c t   ' B Y E F E _ D I R E C T I O N _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d D i r e c t i o n ) , 1 0 ) , N o m , ' B Y E F E _ S O C I E T E _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +     c o n v e r t ( n v a r c h a r ( 3 0 ) , i d S o c i e t e ) , 1 0 ) , ' 0 1 5 ' ,  
 ( s e l e c t   p t y _ i d   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E   w h e r e   p t y _ n a m e   l i k e   ' D i r e c t i o n '   a n d   e n t _ i d = ' 0 1 5 ' ) , 1 , 2   f r o m   H e m e r a . d b o . D i r e c t i o n  
 g o  
 - - s e l e c t   *   f r o m   H e m e r a . d b o . S e r v i c e  
 - -   i n s e r t   S e r v i c e   t o   d b o . P E R _ P E R I M E T R E    
 i n s e r t   i n t o   V I P . d b o . P E R _ P E R I M E T R E ( p e r _ i d , p e r _ n a m e , p e r _ p a r e n t _ i d , e n t _ i d , p t y _ i d , l a g _ i d , e t j _ i d )  
 s e l e c t   ' B Y E F E _ S E R V I C E _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 ' +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d S e r v i c e ) , 1 0 ) , N o m , ' B Y E F E _ D I R E C T I O N _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d D i r e c t i o n ) , 1 0 ) , ' 0 1 5 ' ,  
 ( s e l e c t   p t y _ i d   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E   w h e r e   p t y _ n a m e   l i k e   ' S e r v i c e '   a n d   e n t _ i d = ' 0 1 5 ' ) , 1 , 2   f r o m   H e m e r a . d b o . S e r v i c e  
 g o  
 - - i n s e r t   C h a n t i e r   S e r v i c e   t o   d b o . P E R _ P E R I M E T R E    
 i n s e r t   i n t o   V I P . d b o . P E R _ P E R I M E T R E ( p e r _ i d , p e r _ n a m e , p e r _ p a r e n t _ i d , e n t _ i d , p t y _ i d , p e r _ c h a n t i e r S E P , p e r _ c h a n t i e r I D , p e r _ c h a n t i e r S t a r t D a t e , p e r _ c h a n t i e r P l a n n e d E n d D a t e , p e r _ c h a n t i e r E n d D a t e ,  
 	 	 	 	 	 	 	 	 	 	       l a g _ i d , c t y _ i d , e t j _ i d , p e r _ a d d r e s s , p e r _ c h a n t i e r G r o u p e m e n t , p e r _ c h a n t i e r P a r t e n a i r e s , p e r _ c h a n t i e r N u m e r o D e P r o j e t )  
 s e l e c t   ' B Y E F E _ C H A N T I E R _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 ' +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d C h a n t i e r ) , 1 0 ) , L i b C h a n t i e r , ' B Y E F E _ S E R V I C E _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d S e r v i c e ) , 1 0 ) , ' 0 1 5 ' ,  
 ( s e l e c t   p t y _ i d   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E   w h e r e   p t y _ n a m e   l i k e   ' C h a n t i e r '   a n d   e n t _ i d = ' 0 1 5 ' ) , c a s e   w h e n   I s S E P   =   ' N '   t h e n   0   e l s e   1   e n d , I d C h a n t i e r , D a t e D e b u t , D a t e R e c e p t i o n D e f i n i t i v e , N U L L ,  
 1 , c a s e   w h e n   I d T y p e C h a n t i e r   =   3   t h e n   1 2   w h e n   I d T y p e C h a n t i e r = 4   t h e n   9   w h e n   I d T y p e C h a n t i e r = 5   t h e n   1 1   e n d , 2 , A d r e s s e , c a s e   w h e n   I s G r o u p e m e n t   =   ' N '   t h e n   0   e l s e   1   e n d , P a r t e n a i r e s , C I C h a n t i e r  
 f r o m   H e m e r a . d b o . C h a n t i e r    
 w h e r e   I d E n t i t e = 1   a n d   I d S e r v i c e   i s   n o t   n u l l  
 g o  
 - - i n s e r t   C h a n t i e r   D i r e c t i o n   t o   d b o . P E R _ P E R I M E T R E    
 i n s e r t   i n t o   V I P . d b o . P E R _ P E R I M E T R E ( p e r _ i d , p e r _ n a m e , p e r _ p a r e n t _ i d , e n t _ i d , p t y _ i d , p e r _ c h a n t i e r S E P , p e r _ c h a n t i e r S t a r t D a t e , p e r _ c h a n t i e r P l a n n e d E n d D a t e , p e r _ c h a n t i e r E n d D a t e ,  
 	 	 	 	 	 	 	 	 	 	       l a g _ i d , c t y _ i d , e t j _ i d , p e r _ a d d r e s s , p e r _ c h a n t i e r G r o u p e m e n t , p e r _ c h a n t i e r P a r t e n a i r e s , p e r _ c h a n t i e r N u m e r o D e P r o j e t )  
 s e l e c t   ' B Y E F E _ C H A N T I E R _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 ' +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d C h a n t i e r ) , 1 0 ) , L i b C h a n t i e r , ' B Y E F E _ D I R E C T I O N _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 '   +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d D i r e c t i o n ) , 1 0 ) , ' 0 1 5 ' ,  
 ( s e l e c t   p t y _ i d   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E   w h e r e   p t y _ n a m e   l i k e   ' C h a n t i e r '   a n d   e n t _ i d = ' 0 1 5 ' ) , c a s e   w h e n   I s S E P   =   ' N '   t h e n   0   e l s e   1   e n d , D a t e D e b u t , D a t e R e c e p t i o n D e f i n i t i v e , N U L L ,  
 1 , c a s e   w h e n   I d T y p e C h a n t i e r   =   3   t h e n   1 2   w h e n   I d T y p e C h a n t i e r = 4   t h e n   9   w h e n   I d T y p e C h a n t i e r = 5   t h e n   1 1   e n d , 2 , A d r e s s e , c a s e   w h e n   I s G r o u p e m e n t   =   ' N '   t h e n   0   e l s e   1   e n d , P a r t e n a i r e s , C I C h a n t i e r  
 f r o m   H e m e r a . d b o . C h a n t i e r    
 w h e r e   I d E n t i t e = 1   a n d   I d S e r v i c e   i s   n u l l  
 g o  
  
 - -   i n s e r t   a   n e w   d n a   H e m e r a ( B Y E F E )  
 s e t   i d e n t i t y _ i n s e r t   V I P . d b o . D N A _ D E L E G A T I O N _ N A T U R E   o n  
 g o  
 i n s e r t   i n t o   V I P . d b o . D N A _ D E L E G A T I O N _ N A T U R E ( d n a _ i d , d n a _ n a m e , d n a _ d e s c r i p t i o n , e n t _ i d )  
 s e l e c t   1 0 0 0 0 , ' H e m e r a ' , ' H e m e r a ' , ' 0 1 5 '  
 s e t   i d e n t i t y _ i n s e r t   V I P . d b o . D N A _ D E L E G A T I O N _ N A T U R E   o f f  
 g o  
  
 - - -   i n s e r t   a   n e w   H e m e r a   d s t  
 i n s e r t   i n t o   V I P . d b o . D S T _ D E L E G A T I O N _ S T A T U S    
 s e l e c t   ' H _ T ' , ' H e m e r a   S t a t u t   T '  
 u n i o n    
 s e l e c t   ' H _ V ' , ' H e m e r a   S t a t u t   V '  
 g o  
  
 - -   i n s e r t   n e w   B Y E F E   d b o . D O M _ D O C U M E N T _ M O D E L  
 i n s e r t   i n t o   V I P . d b o . D O M _ D O C U M E N T _ M O D E L  
 s e l e c t   ' B Y E F E _ D o m _ D o c u m e n t _ M o d e l ' , ' B Y E F E . d o c ' , ' D P ' , ' ' , 1 , ' 0 1 5 ' , N U L L , N U L L  
 g o  
  
 - - i n s e r t   d a t a   B Y E F E   d b o . D O M _ D E L    
 i n s e r t   i n t o   V I P . d b o . D O M _ D E L  
 s e l e c t   ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E _ D o m _ D o c u m e n t _ M o d e l ' ) , I d D e l e g a t i o n   +   1 0 0 0 0 ,  
 	 	 n u l l , D a t e S i g n a t u r e , D e l e g a t i o n S i g n e e , n u l l  
 f r o m   H e m e r a . d b o . D e l e g a t i o n  
 w h e r e   I d C h a n t i e r   i n   ( s e l e c t   I d C h a n t i e r   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e   I d E n t i t e = 1 )  
 g o  
  
 - -   i n s e r t   B Y E F E   t o   t a b l e   d b o . D E M _ D O M  
 i n s e r t   i n t o   V I P . d b o . D E M _ D O M  
 s e l e c t   ( s e l e c t   m a x ( d e m _ g r o u p ) + 1   f r o m   V I P . d b o . D E M _ D O M ) ,  
 d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % '  
 g o  
 - -   i n s e r t   B Y E F E   t o   t a b l e   d b o . D E M _ D E L E G A T I O N _ M O D E L    
 i n s e r t   i n t o   V I P . d b o . D E M _ D E L E G A T I O N _ M O D E L  
 s e l e c t   1 ,   p t y _ i d , 1 0 0 0 0 , N U L L , ' 0 1 5 ' , ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,  
 0 , 0 , 0   f r o m   V I P . d b o . P T Y _ P E R I M E T R E _ T Y P E  
 w h e r e   e n t _ i d = ' 0 1 5 '  
 g o  
 - - I n s e r t   i n t o   t a b l e   d b o . F I R _ F I E L D _ R U L E  
  
 d e c l a r e   @ f i e _ i d   i n t  
 d e c l a r e   c u r s o r _ f i e _ i d   c u r s o r   f o r  
 s e l e c t   d i s t i n c t   f i e _ i d   f r o m   F I E _ F I E L D   w h e r e   e n t _ i d = ' 0 1 5 '  
 o p e n   c u r s o r _ f i e _ i d  
 f e t c h   n e x t   f r o m   c u r s o r _ f i e _ i d   I N T O   @ f i e _ i d  
 	 w h i l e   @ @ F E T C H _ S T A T U S   =   0  
 	 	 b e g i n  
 	 	  
 	 	 	 i n s e r t   i n t o   F I R _ F I E L D _ R U L E   ( d e m _ g r o u p ,   f i r _ d i s p l a y e d ,   f i e _ i d )    
 	 	 	 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   0 ,   @ f i e _ i d 	    
 	 	 	 f e t c h   n e x t   f r o m   c u r s o r _ f i e _ i d   i n t o   @ f i e _ i d 	  
 	         e n d  
 	 c l o s e   c u r s o r _ f i e _ i d  
 	 d e a l l o c a t e   c u r s o r _ f i e _ i d  
 g o  
 - -   i n s e r t   B Y E F E   t o   t a b l e   d b o . F I R _ F I E L D _ R U L E  
  
 i n s e r t   i n t o   F I R _ F I E L D _ R U L E   ( d e m _ g r o u p ,   f i r _ d i s p l a y e d ,   f i e _ i d )    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   7 8 	  
 u n i o n    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   1 0 7  
 u n i o n    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   7 6  
 u n i o n    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   1 0 6  
 u n i o n    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   1 1 0  
 u n i o n    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   1 0 4 	  
 u n i o n    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   8 0 	 	 	 	  
 u n i o n    
 s e l e c t     ( s e l e c t   t o p   1   d e m _ g r o u p   f r o m   V I P . d b o . D E M _ D O M   w h e r e   d o m _ i d   =  
 	 	 ( s e l e c t   t o p   1   d o m _ i d   f r o m   V I P . d b o . D O M _ D O C U M E N T _ M O D E L   w h e r e   d o m _ n a m e   l i k e   ' B Y E F E % ' ) ) ,   1 ,   1 0 2 	  
 g o  
 - - - i n s e r t   B Y E F E   t o   t a b l e   d b o . C O T _ C O L L A B O R A T E U R _ T Y P E  
 i n s e r t   i n t o   V I P . d b o . C O T _ C O L L A B O R A T E U R _ T Y P E  
 s e l e c t   ' C O L _ B Y E F E ' , ' C O L L A B O R A T E U R   o f   B Y E F E ' , ' 0 1 5 ' , 2  
 g o  
 - -   i n s e r t   d a t a   t o   d b o . D E L _ D E L E G A T I O N  
 s e t   i d e n t i t y _ i n s e r t   V I P . d b o . D E L _ D E L E G A T I O N   o n  
 g o  
 i n s e r t   i n t o   V I P . d b o . D E L _ D E L E G A T I O N      
 	 ( [ d e l _ i d ] , [ d e l _ p a r e n t _ i d ] , [ d t y _ i d ] , [ d e l _ s t a r t D a t e ] , [ d e l _ e n d D a t e ] , [ d e l _ i s S i g n e d ] , [ d e l _ d e l e g a t i o n C o n j o i n t e ] , [ d e l _ d a t e 1 ]   , [ d e l _ p l a c e 1 ]  
 	 , [ d e l _ d a t e 2 ] , [ d e l _ p l a c e 2 ] , [ d e l _ d a t e 3 ]   , [ d e l _ p l a c e 3 ] , [ d e l _ a m o u n t 1 ] , [ d e l _ a m o u n t 2 ] , [ d e l _ a m o u n t 3 ] , [ d e l _ a m o u n t 4 ] , [ d e l _ a m o u n t 5 ] , [ d e l _ c o m m e n t 1 ]  
 	 , [ d e l _ c o m m e n t 2 ] , [ d s t _ i d ]   , [ d n a _ i d ] , [ e n t _ i d ] , [ d e l _ e t j _ i d ] , [ d e l _ e t j _ n a m e ] , [ d e l _ e t j _ c a p i t a l ] , [ d e l _ e t j _ r e g i s t r a t i o n I d ] , [ d e l _ e t j _ r e g i s t r a t i o n A d d r e s s ]  
 	 , [ d e l _ d e l e g a t a i r e Q u a l i t e ] , [ d e l _ d e l e g a n t Q u a l i t e ] , [ p e r _ i d ] , [ d e l _ p e r _ c h a n t i e r N a m e ] , [ d e l _ p e r _ c h a n t i e r I D ] , [ d e l _ p e r _ c h a n t i e r S t a r t D a t e ] , [ d e l _ p e r _ c h a n t i e r P l a n n e d E n d D a t e ]  
 	 , [ d e l _ p e r _ c h a n t i e r E n d D a t e ] , [ d e l _ p a r t y D e l e g e e ] , [ d e l _ e t j _ a d d r e s s ] , [ d e l _ d e s c r i p t i o n ] , [ d e l _ e t j _ s t a t u t ] , [ d e l _ d e l e g a n t _ i d ] , [ d e l _ d e l e g a n t F i r s t n a m e ] , [ d e l _ d e l e g a n t L a s t n a m e ]  
 	 , [ d e l _ d e l e g a n t _ n i v e a u H i e r a r c h i q u e ] , [ d e l _ d e l e g a n t _ l i e u N a i s s a n c e ] , [ d e l _ d e l e g a n t _ d a t e N a i s s a n c e ] , [ d e l _ d e l e g a t a i r e _ i d ] , [ d e l _ d e l e g a t a i r e F i r s t n a m e ] , [ d e l _ d e l e g a t a i r e L a s t n a m e ]  
 	 , [ d e l _ d e l e g a t a i r e _ n i v e a u H i e r a r c h i q u e ] , [ d e l _ d e l e g a t a i r e _ l i e u N a i s s a n c e ] , [ d e l _ d e l e g a t a i r e _ d a t e N a i s s a n c e ] , [ d e l _ d e l e g a n t _ n a t i o n a l i t y ] , [ d e l _ d e l e g a t a i r e _ n a t i o n a l i t y ]  
 	 , [ d e l _ p e r _ c h a n t i e r C i t y ] , [ d e l _ d e l e g a n t T i t l e ] , [ d e l _ d e l e g a t a i r e S t a t u t ] , [ d e l _ d e l e g a t a i r e A d d r e s s ] , [ d e l _ d e l e g a n t S t a t u t ] , [ d e l _ z o n e ] , [ d e l _ o p e r a t i o n s ] , [ d e l _ d e m a n d e u r _ i d ]  
 	 , [ d e l _ d e m a n d e u r F i r s t n a m e ] , [ d e l _ d e m a n d e u r L a s t n a m e ] )  
 s e l e c t   I d D e l e g a t i o n   +   1 0 0 0 0 , I d D e l e g a t i o n P r i n c i p a l e   +   1 0 0 0 0 ,  
 	 c a s e   w h e n   T y p e D e l e g a t i o n = ' m a i n '   t h e n   1   w h e n   T y p e D e l e g a t i o n = ' s u b '   t h e n   2   e l s e   3   e n d ,  
         D a t e D e b u t , D a t e F i n   ,  
         c a s e   w h e n   D e l e g a t i o n S i g n e e   i s   n o t   n u l l   t h e n   1   e l s e   0   e n d ,  
 	 c a s e   w h e n   D e l e g a t i o n C o n j o i n t e = ' N '   t h e n   0   e l s e   1   e n d ,  
 	 D a t e R e c o m m a n d a t i o n , L i e u R e c o m m a n d a t i o n , D a t e S i g n a t u r e , L i e u S i g n a t u r e , N u l l , N u l l , N u l l , N u l l , N u l l , N u l l , N u l l , N u l l , N u l l ,  
 	 c a s e   w h e n   S t a t u t = ' D '   t h e n   ( s e l e c t   t o p   1   d s t _ i d   f r o m   V I P . d b o . D S T _ D E L E G A T I O N _ S T A T U S   w h e r e   d s t _ n a m e   l i k e   ' S i g n � e ' )  
 	 w h e n   S t a t u t = ' O '   t h e n   ( s e l e c t   t o p   1   d s t _ i d   f r o m   V I P . d b o . D S T _ D E L E G A T I O N _ S T A T U S   w h e r e   d s t _ n a m e   l i k e   ' O b s o l � t e ' )    
 	 w h e n   S t a t u t = ' P '   t h e n   ( s e l e c t   t o p   1   d s t _ i d   f r o m   V I P . d b o . D S T _ D E L E G A T I O N _ S T A T U S   w h e r e   d s t _ n a m e   l i k e   ' % � t a b l i s s e m e n t ' )      
 	 w h e n   S t a t u t = ' V '   t h e n   ( s e l e c t   t o p   1   d s t _ i d   f r o m   V I P . d b o . D S T _ D E L E G A T I O N _ S T A T U S   w h e r e   d s t _ n a m e   l i k e   ' H _ V ' )  
 	 w h e n   S t a t u t = ' T '   t h e n   ( s e l e c t   t o p   1   d s t _ i d   f r o m   V I P . d b o . D S T _ D E L E G A T I O N _ S T A T U S   w h e r e   d s t _ n a m e   l i k e   ' H _ T ' )  
 	   e n d , 1 0 0 0 0 , ' 0 1 5 ' , 2 ,  
         ( s e l e c t   e t j _ n a m e   f r o m   V I P . d b o . E T J _ E N T I T E _ J U R I D I Q U E   w h e r e   e t j _ i d = 2 ) ,  
         ( s e l e c t   e t j _ c a p i t a l   f r o m   V I P . d b o . E T J _ E N T I T E _ J U R I D I Q U E   w h e r e   e t j _ i d = 2 ) ,  
         ( s e l e c t   e t j _ r e g i s t r a t i o n I d   f r o m   V I P . d b o . E T J _ E N T I T E _ J U R I D I Q U E   w h e r e   e t j _ i d = 2 ) ,  
         ( s e l e c t   e t j _ r e g i s t r a t i o n A d d r e s s   f r o m   V I P . d b o . E T J _ E N T I T E _ J U R I D I Q U E   w h e r e   e t j _ i d = 2 ) ,  
         ( s e l e c t   t o p   1   Q u a l i t e D e l e g a n t   f r o m     H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r   =   H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t i o n   a n d   I s D e l e g a n t = ' O ' ) ,  
         ( s e l e c t   t o p   1   Q u a l i t e D e l e g a n t   f r o m     H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r   =   H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t a i r e   a n d   I s D e l e g a t a i r e = ' O ' ) ,  
         - - c a s e   w h e n   ( s e l e c t   t o p   1   I d E n t i t e   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e     I d C h a n t i e r = H e m e r a . d b o . D e l e g a t i o n . I d C h a n t i e r )   = 4   t h e n     ' B Y T P _ C H A N T I E R _ ' +   c o n v e r t ( v a r c h a r ( 3 0 ) , I d C h a n t i e r )  
       c a s e   w h e n   ( s e l e c t   t o p   1   I d E n t i t e   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e     I d C h a n t i e r = H e m e r a . d b o . D e l e g a t i o n . I d C h a n t i e r )   = 1   t h e n   ' B Y E F E _ C H A N T I E R _ _ _ _ _ _ _ _ _ _ _ ' +   R I G H T ( ' 0 0 0 0 0 0 0 0 0 0 ' +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d C h a n t i e r ) , 1 0 )   e n d , - -   ' B Y E F E _ C H A N T I E R _ ' +   c o n v e r t ( v a r c h a r ( 3 0 ) , I d C h a n t i e r )   e n d ,  
         ( s e l e c t   t o p   1   L i b C h a n t i e r   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e   I d C h a n t i e r = H e m e r a . d b o . D e l e g a t i o n . I d C h a n t i e r ) , N u l l ,  
 	 ( s e l e c t   t o p   1   D a t e D e b u t   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e   I d C h a n t i e r = H e m e r a . d b o . D e l e g a t i o n . I d C h a n t i e r ) ,  
 	 ( s e l e c t   t o p   1   D a t e R e c e p t i o n P r o v   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e   I d C h a n t i e r = H e m e r a . d b o . D e l e g a t i o n . I d C h a n t i e r ) ,  
 	 ( s e l e c t   t o p   1   D a t e R e c e p t i o n D e f i n i t i v e   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e   I d C h a n t i e r = H e m e r a . d b o . D e l e g a t i o n . I d C h a n t i e r ) , N u l l ,  
 	 ( s e l e c t   e t j _ a d d r e s s   f r o m   V I P . d b o . E T J _ E N T I T E _ J U R I D I Q U E   w h e r e   e t j _ i d = 2 ) , D e t a i l , N u l l , I d D e l e g a n t + 1 0 0 0 0 ,  
 	 ( s e l e c t   t o p   1   N o m   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a n t   a n d   I s D e l e g a n t = ' O ' ) ,  
 	 ( s e l e c t   t o p   1   P r e n o m   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a n t   a n d   I s D e l e g a n t = ' O ' ) ,  
 	 ( s e l e c t   t o p   1   N i v e a u H i e r a r c h i q u e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a n t   a n d   I s D e l e g a n t = ' O ' ) ,  
 	 ( s e l e c t   t o p   1   L i e u N a i s s a n c e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a n t   a n d   I s D e l e g a n t = ' O ' ) ,  
 	 ( s e l e c t   t o p   1   D a t e N a i s s a n c e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a n t   a n d   I s D e l e g a n t = ' O ' ) ,  
 	   I d D e l e g a t a i r e   +   1 0 0 0 0 ,  
 	   ( s e l e c t   t o p   1   N o m   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t a i r e   a n d   I s D e l e g a t a i r e = ' O ' ) ,  
 	   ( s e l e c t   t o p   1   P r e n o m   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t a i r e   a n d   I s D e l e g a t a i r e = ' O ' ) ,  
 	   ( s e l e c t   t o p   1   N i v e a u H i e r a r c h i q u e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t a i r e   a n d   I s D e l e g a t a i r e = ' O ' ) ,  
 	   ( s e l e c t   t o p   1   L i e u N a i s s a n c e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t a i r e   a n d   I s D e l e g a t a i r e = ' O ' ) ,  
 	   ( s e l e c t   t o p   1   D a t e N a i s s a n c e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t a i r e   a n d   I s D e l e g a t a i r e = ' O ' ) ,  
 	   ( s e l e c t   t o p   1   N a t i o n a l i t e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a n t   a n d   I s D e l e g a n t = ' O ' ) ,  
 	   ( s e l e c t   t o p   1   N a t i o n a l i t e   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   w h e r e   I d C o l l a b o r a t e u r = H e m e r a . d b o . D e l e g a t i o n . I d D e l e g a t a i r e   a n d   I s D e l e g a t a i r e = ' O ' ) ,  
 	   ( s e l e c t   t o p   1   V i l l e   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e   I d C h a n t i e r = H e m e r a . d b o . D e l e g a t i o n . I d C h a n t i e r ) , ' ' , ' ' , ' ' , ' ' , ' ' , ' ' , D e m a n d e u r   +   1 0 0 0 0 , ' ' , ' '  
 	          
 f r o m   H e m e r a . d b o . D e l e g a t i o n  
 w h e r e   I d C h a n t i e r   i n   ( s e l e c t   I d C h a n t i e r   f r o m   H e m e r a . d b o . C h a n t i e r   w h e r e   I d E n t i t e = 1 )  
  
 s e t   i d e n t i t y _ i n s e r t   V I P . d b o . D E L _ D E L E G A T I O N   o f f  
 g o  
  
 - -   u p d a t e   d b o . D E L _ D E L E G A T I O N   d e l _ e n d D a t e  
 u p d a t e   V I P . d b o . D E L _ D E L E G A T I O N  
 s e t   d e l _ e n d D a t e = ' 2 0 1 5 - 1 2 - 2 5   0 0 : 0 0 : 0 0 . 0 0 0 '  
 w h e r e   e n t _ i d = ' 0 1 5 '  
 g o  
 - -   i n s e r t   d a t a   c o n t r o l  
 i n s e r t   i n t o   V I P . d b o . C O N _ C O N T R O L _ T Y P E  
 s e l e c t   ' T r a v a i l   I l l � g a l   :   A u d i t   I n i t i a l   I n t e r n e ' , ' T r a v a i l   I l l � g a l   :   A u d i t   I n i t i a l   I n t e r n e ' , ' 0 1 5 '  
 u n i o n  
 s e l e c t   ' T o u l o u s e   S t   M i c h e l ' , ' T o u l o u s e   S t   M i c h e l ' , ' 0 1 5 '  
 g o  
  
 i n s e r t   i n t o   V I P . d b o . C T L _ C O N T R O L  
 s e l e c t   ' B Y E F E _ C H A N T I E R _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 ' +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d C h a n t i e r ) , 1 0 ) ,  
 	 	 ' B Y E F E _ C H A N T I E R _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 ' +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d C h a n t i e r ) , 1 0 ) ,  
 	 	 ( s e l e c t   t o p   1   p e r _ p a r e n t _ i d   f r o m   V I P . d b o . P E R _ P E R I M E T R E   w h e r e   p e r _ i d =   ' B Y E F E _ C H A N T I E R _ _ _ _ _ _ _ _ _ _ _ ' +   r i g h t ( ' 0 0 0 0 0 0 0 0 0 0 ' +   c o n v e r t ( n v a r c h a r ( 3 0 ) , I d C h a n t i e r ) , 1 0 ) ) ,  
 	 	 I d T y p e C o n t r o l e , D a t e , C a r a c t e r e , c a s e   w h e n   I d C o l l a b o r a t e u r   I S   N U L L     t h e n   N u l l   e l s e   I d C o l l a b o r a t e u r   +   1 0 0 0 0   e n d , R a p p o r t  
 	 	  
 f r o m   H e m e r a . d b o . C o n t r o l e  
 g o  
  
 - - i n s e r t   d a t a   t o   d b o . C O L _ C O L L A B O R A T E U R  
 s e t   i d e n t i t y _ i n s e r t   V I P . d b o . C O L _ C O L L A B O R A T E U R   o n  
 g o  
 i n s e r t   i n t o   V I P . d b o . C O L _ C O L L A B O R A T E U R   ( c o l _ i d , c o l _ c i v i l i t e , c o l _ f i r s t n a m e , c o l _ l a s t n a m e , c o l _ n a t i o n a l i t y , c o l _ d a t e E n t r e e , c o l _ d a t e N a i s s a n c e ,  
 	 	 	 	 	 	 	 	 	 	 	 	 c o l _ i d B y c n , e n t _ i d , c o l _ i s D e l e g a n t , c o l _ i s D e l e g a t a i r e , c o l _ d a t e S o r t i e , c o l _ n i v e a u H i e r a r c h i q u e , c o l _ l i e u N a i s s a n c e , c o t _ i d ,  
 	 	 	 	 	 	 	 	 	 	 	 	 c o l _ q u a l i t e D e l e g a n t , c o l _ d a t e C o n s e i l , c o l _ s t a t u t C o n s e i l , c o l _ d a t e E f f e t , c o l _ d a t e D e l e g a t i o n , c o l _ d e l e g a n t I d ,  
 	 	 	 	 	 	 	 	 	 	 	 	 c o l _ q u a l i t e C o l D e l e g a n t , c o l _ a d d r e s s , c o l _ d a t e M a j R u b i s , c o l _ s o r t i )  
 s e l e c t   A . I d C o l l a b o r a t e u r   +   1 0 0 0 0   , c a s e   w h e n   A . C i v i l i t e   = ' W '   t h e n   ' M a d a m e '   w h e n   A . C i v i l i t e = ' G '   t h e n   ' M a d e m o i s e l l e '   w h e n   A . C i v i l i t e = ' M '   t h e n   ' M o n s i e u r '   e l s e ' '   e n d ,  
 	 	 A . P r e n o m , A . N o m , A . N a t i o n a l i t e , c a s e   w h e n   A . D a t e E n t r e e G r o u p e   i s   n u l l   t h e n   ' '   e l s e   A . D a t e E n t r e e G r o u p e   e n d   ,  
 	 	 A . D a t e N a i s s a n c e , c a s e   w h e n   M a t r i c u l e R u b i s   i s   n u l l   t h e n   ' '   e l s e   M a t r i c u l e R u b i s   e n d   , ' 0 1 5 ' , c a s e   w h e n   A . I s D e l e g a n t = ' N '   t h e n   0   e l s e   1   e n d , c a s e   w h e n   A . I s D e l e g a t a i r e = ' N '   t h e n   0   e l s e   1   e n d   ,  
 	 	 A . D a t e S o r t i e S o c i e t e , A . N i v e a u H i e r a r c h i q u e , A . L i e u N a i s s a n c e , ( s e l e c t     t o p   1   c o t _ i d   f r o m   V I P . d b o . C O T _ C O L L A B O R A T E U R _ T Y P E  
 	 	 	 	 	 	 	 	 	 	 	 	 	 	 	 	     w h e r e   e n t _ i d = ' 0 1 5 '   a n d   c o t _ n a m e   l i k e   ' C O L _ B Y E F E ' ) ,  
 	 	 B . Q u a l i t e D e l e g a n t , B . D a t e C o n s e i l , B . S t a t u t C o n s e i l ,  
 	 	 N U L L , B . D a t e D e l e g a t i o n , B . I d C o l l a b o r a t e u r D e l e g a n t , n u l l , A . A d r e s s e , A . D a t e M a j R u b i s , A . I s S o r t i  
   f r o m   H e m e r a . d b o . C o l l a b o r a t e u r   a s   A   l e f t   j o i n   H e m e r a . d b o . J o i n C o l l a b o r a t e u r D e l e g a n t T o U o   a s   B   O N   A . I d C o l l a b o r a t e u r = B . I d C o l l a b o r a t e u r  
  
 s e t   i d e n t i t y _ i n s e r t   V I P . d b o . C O L _ C O L L A B O R A T E U R   o f f  
 g o  
 