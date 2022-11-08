DROP TABLE IF EXISTS `nomenclature`;


CREATE TABLE `nomenclature`(
  `order_Id` int PRIMARY KEY,
  `level` int,
  `code` varchar(100),
  `parent` varchar(100),
  `description` varchar(100),
  `item_include` CLOB,
  `itemalso_include` CLOB,
  `rulings` varchar(100),
  `item_exclude` CLOB,
  `reference` varchar(100)
  );
  

INSERT INTO `nomenclature`
(`order_Id`,`level`,`code`,`parent`,`description`,`item_include`,`itemalso_include`,`rulings`,`item_exclude`,`reference`)
values
(389492,
1,
'A',
'1',
'Crop and animal production, hunting and related service activities',
'This division includes two basic activities, namely the production of crop products
 and production of animal products, covering also the forms of organic agriculture, 
 the growing of genetically modified crops and the raising of genetically modified animals. 
 This division includes growing of crops in open fields as well in greenhouses.',
'This division also includes service activities incidental to agriculture, as well as hunting, trapping and related activities.',
'NAME',
'Agricultural activities exclude any subsequent processing 
of the agricultural products (classified under divisions 10 and 11 (Manufacture of food products and beverages)
and division 12 (Manufacture of tobacco products)), beyond that needed to prepare them for the primary markets. 
The preparation of products for the primary markets is included here.',
'A');

INSERT INTO `nomenclature`
(`order_Id`,`level`,`code`,`parent`,`description`,`item_include`,`itemalso_include`,`rulings`,`item_exclude`,`reference`)
values
(389493,
3,
'1.1',
'1',
'Growing of non-perennial crops',
'This group includes the growing of non-perennial crops, i.e. plants that do not last for more than two growing seasons. Included is the growing of these plants for the purpose of seed production.',
'This division also includes service activities incidental to agriculture, as well as hunting, trapping and related activities.',
'',
'This class excludes:
- manufacture of tobacco products, see 12.00',
'1.15');

INSERT INTO `nomenclature`
(`order_Id`,`level`,`code`,`parent`,`description`,`item_include`,`itemalso_include`,`rulings`,`item_exclude`,`reference`)
values
(398515,
4,
'1.61',
'1.6',
'Support activities for crop production',
'This class includes:
- agricultural activities on a fee or contract basis:
  . preparation of fields
  . establishing a crop
  . treatment of crops
  . crop spraying, including by air
  . trimming of fruit trees and vines
  . transplanting of rice, thinning of beets
  . harvesting
  . pest control (including rabbits) in connection with agriculture
- maintenance of agricultural land in good agricultural and environmental condition
- operation of agricultural irrigation equipment',
'This class also includes:
- provision of agricultural machinery with operators and crew',
'=- Nurturing and selling of tree seedlings',
'This class excludes:
- post-harvest crop activities, see 01.63
- drainage of agricultural land, see 43.12
- landscape architecture, see 71.11
- activities of agronomists and agricultural economists, see 74.90
- landscape gardening, planting, see 81.30
- organisation of agricultural shows and fairs, see 82.30',
'163');