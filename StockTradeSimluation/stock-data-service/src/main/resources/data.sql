CREATE TABLE IF NOT EXISTS Userdb (
  id VARCHAR(250) PRIMARY KEY,
  stock_list VARCHAR(250)  NULL,
  balance VARCHAR(250)  NULL,
  profit VARCHAR(250)  NULL,
  no_of_stock VARCHAR(250)  NULL,
  name VARCHAR(250)  NULL,
  email VARCHAR(250)  NULL,
  mobile VARCHAR(250)  NULL,
  password VARCHAR(250)  NULL,
  premium_customer VARCHAR(250)  NULL
);

CREATE TABLE IF NOT EXISTS Stock (
  id VARCHAR(250) PRIMARY KEY,
  open_val VARCHAR(250)  NULL,
  high VARCHAR(250)  NULL,
  low VARCHAR(250)  NULL,
  price VARCHAR(250)  NULL,
  volume VARCHAR(250)  NULL,
  latest_trading_day VARCHAR(250)  NULL,
  previous_close VARCHAR(250)  NULL,
  change VARCHAR(250)  NULL,
  change_percent VARCHAR(250)  NULL
);

CREATE TABLE IF NOT EXISTS StockSymbol (
  id VARCHAR(250) PRIMARY KEY,
  name VARCHAR(250)  NULL,
);

CREATE TABLE IF NOT EXISTS StockUser (
  id VARCHAR(250) PRIMARY KEY,
  stock_name VARCHAR(250)  NULL,
  stock_symbol VARCHAR(250)  NULL,
  stock_volume VARCHAR(250)  NULL,
  user_id VARCHAR(250)  NULL,
  current_value VARCHAR(250)  NULL,
  price_of_stock VARCHAR(250)  NULL,
  change_percent VARCHAR(250)  NULL,

);




