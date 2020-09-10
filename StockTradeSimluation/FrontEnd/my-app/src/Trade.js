import React from 'react';
import { Typography, Button, TextField, TableContainer, Table, TableHead, TableRow, TableCell, TableBody } from '@material-ui/core';
import TradeImg from './assets/Trade.jpg';
import buyImg from './assets/buy.jpg';
import sellImg from './assets/sell.jpg';
import Autocomplete from '@material-ui/lab/Autocomplete';
import Paper from '@material-ui/core/Paper';
import symbolList from './symbolList.json'
import ImgMediaCard from './Cards';

class Trade extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userID: "sample",
            mode: "none",
            portfolio: null,
            stock_q: null,
            volume: 0,
            amount: 0,

            bStock: null,
            bStockData: null,
            bStockVolume: 0,
            bStockAmount: 0
        }
        this.onTagsChange = this.onTagsChange.bind(this);
        this.updateAmount = this.updateAmount.bind(this);
        this.onbUpdateAmount = this.onbUpdateAmount.bind(this);
        this.buy = this.buy.bind(this);
        this.sell = this.sell.bind(this);
        this.home = this.home.bind(this);
        this.onStockSearch = this.onStockSearch.bind(this);
        this.invalidStock = this.invalidStock.bind(this);
        this.tradeBuy = this.tradeBuy.bind(this);
    }

    buy() {
        this.setState({
            mode: "buy"
        })
    }

    sell() {
        this.setState({
            mode: "sell"
        })
        //CALL REST
        var url = 'http://localhost:8983/userService/portfolio/' + this.state.userID
        fetch(url, {
            method: 'GET', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                this.setState({ portfolio: data })
                console.log('Success Portfolio:', data);
            })
            .catch((error) => {
                this.setState({ portfolio: null })
                console.error('Error:', error);
            });
    }

    home() {
        this.setState({
            mode: "none",
            portfolio: null,
            stock_q: null,
            volume: 0,
            amount: 0,

            bStock: null,
            bStockData: null,
            bStockVolume: 0,
            bStockAmount: 0
        })
    }
    onStockSearch(e) {
        this.setState({ bStock: e.target.value })
    }

    invalidStock() {
        if (this.state.bStockData == null) {
            alert("Couldn't Find ")
            this.setState({ bStock: null })
            return
        }
    }

    hitStockSerach() {
        if (this.state.bStock == null || this.state.bStock == "") {
            alert("Empty Stock Symbol")
            this.setState({ bStock: null })
            return
        }

        var allow = "false"
        var stockSymbol = this.state.bStock
        symbolList.map(
            (stock) => (
                (stock.id == stockSymbol) ? allow = "true" : null
            )
        )

        if (allow == "false") {
            alert("Invalid Stock Symbol \n Hint : USE CAPS")
            this.setState({ bStock: null })
            return
        }

        var url = 'http://localhost:8982/dataService/get/stock/' + this.state.bStock
        fetch(url, {
            method: 'GET', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                this.setState({ bStockData: data }, () => (this.invalidStock()))
                console.log('Search :', data);
            })
            .catch((error) => {
                this.setState({ bStockData: null })
                console.error('Error:', error);
            });
    }

    tradeSell(){
        var name = ""
        var symbol = ""
        this.state.portfolio.map((e) => (this.state.stock_q == e.id ? name = e.stock_name : null))
        this.state.portfolio.map((e) => (this.state.stock_q == e.id ? symbol = e.stock_symbol : null))

        var reqdata = {
            "stockSymbol": symbol,
            "stockName": name,
            "stockVolume": this.state.volume,
            "amount": this.state.amount
        }
        var url = 'http://localhost:8983/userService/update/user/' + this.state.userID + '/' + 'false'
        fetch(url, {
            method: 'PUT', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(reqdata),
        })
            .then(response => response.json())
            .then(data => {
                console.log(' trade sell:', data);
                alert(data)
                this.setState({ volume: "0" })
                this.setState({ amount: "0" })
                this.sell()
            })
            .catch((error) => {
                alert(error)
                this.setState({ volume: "0" })
                this.setState({ amount: "0" })
                console.error('Error:', error);
            });
    }

    tradeBuy() {
        var reqdata = {
            "stockSymbol": this.state.bStockData["01. symbol"],
            "stockName": this.state.bStockData["01. symbol"],
            "stockVolume": this.state.bStockVolume,
            "amount": this.state.bStockAmount
        }
        var url = 'http://localhost:8983/userService/update/user/' + this.state.userID + '/' + 'true'
        fetch(url, {
            method: 'PUT', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(reqdata),
        })
            .then(response => response.json())
            .then(data => {
                console.log(' trade buy:', data);
                alert(data)
                this.setState({ bStockVolume: "0" })
                this.setState({ bStockAmount: "0" })
            })
            .catch((error) => {
                alert(error)
                this.setState({ bStockVolume: "0" })
                this.setState({ bStockAmount: "0" })
                console.error('Error:', error);
            });

    }

    onTagsChange = (event, values) => {
        this.setState({
            tags: values
        }, () => (
            (this.state.tags != null) ? this.setState({ stock_q: this.state.tags.id }) : this.setState({ stock_q: null })
        ));
    }

    onbVolume(e) {
        if (e.target.value.includes("-")) {
            alert("Stock Volume Cannot be Negative");
            this.setState({ bStockVolume: 0 }, () => (this.onbUpdateAmount()))
        }
        else if(parseFloat(e.target.value)>100){
            alert("Stock Volume Cannot be more than 100!! \n Get Pro for more");
            this.setState({ bStockVolume: 100 }, () => (this.onbUpdateAmount()))
        }
        else
            this.setState({ bStockVolume: e.target.value }, () => (this.onbUpdateAmount()))
        
    }
    onbUpdateAmount() {
        var localAmount = this.state.bStockData["05. price"]
        var tradeAmount = "0"
        tradeAmount = String(parseFloat(this.state.bStockVolume) * parseFloat(localAmount))
        this.setState({ bStockAmount: tradeAmount })
    }

    onVolume(e) {
        if (e.target.value.includes("-")) {
            alert("Stock Volume Cannot be Negative");
            this.setState({ volume: 0 }, () => (this.updateAmount()))
        }
        else {
            var maxvolume = "0"
            this.state.portfolio.map((e) => (this.state.stock_q == e.id ? maxvolume = e.stock_volume : null))
            maxvolume = parseFloat(maxvolume)
            if (parseFloat(e.target.value) > maxvolume) {
                this.setState({ volume: maxvolume }, () => (this.updateAmount()))
                alert("Stock Volume Cannot be more than owned");
            }
            else
                this.setState({ volume: e.target.value }, () => (this.updateAmount()))
        }
    }

    updateAmount() {
        var localAmount = "0"
        var tradeAmount = "0"
        this.state.portfolio.map((e) => (this.state.stock_q == e.id ? localAmount = e.price_of_stock : null))
        tradeAmount = String(parseFloat(this.state.volume) * parseFloat(localAmount))
        this.setState({ amount: tradeAmount })
    }

    render() {
        return (
            <div >
                {(this.state.mode == "none" ?
                    <div style={{
                        backgroundImage: "url(" + TradeImg + ")",
                        backgroundPosition: 'center',
                        backgroundSize: 'cover',
                        backgroundRepeat: 'no-repeat'
                    }}>
                        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                        <center>
                            <Button size="large" onClick={this.buy} variant="contained" color="primary" >Let's Buy something</Button> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <Button size="large" onClick={this.sell} variant="contained" color="secondary" >It's the time to Sell</Button>
                            <br /><br /><br /><br />  <br /><br /><br /><br /> <br /><br /><br /><br />
                        </center>
                    </div> : null)}

                {(this.state.mode == "buy" ?
                    <div style={{
                        backgroundImage: "url(" + buyImg + ")",
                        backgroundPosition: 'center',
                        backgroundSize: 'cover',
                        backgroundRepeat: 'no-repeat'
                    }}>
                        <Button size="large" onClick={this.home} variant="contained" color="primary" >Go Back</Button>
                        <br /><br />
                        <TextField onChange={this.onStockSearch} autoFocus="true" label="Enter Stock Symbol" variant="outlined" /> &nbsp;&nbsp;
                        <Button size="large" onClick={this.hitStockSerach.bind(this)} variant="contained" color="primary" >Find stock</Button><br /><br />

                        {this.state.bStockData == null ? <div><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /></div> : <div>
                            <h2>Real Time Stock Information</h2>
                            <tr>
                                <td><ImgMediaCard title="Open" value={this.state.bStockData["02. open"]} /></td><td>&nbsp;</td>
                                <td><ImgMediaCard title="High" value={this.state.bStockData["03. high"]} /></td><td>&nbsp;</td>
                                <td><ImgMediaCard title="Low" value={this.state.bStockData["04. low"]} /></td><td>&nbsp;</td>
                                <td><ImgMediaCard title="Previous Close" value={this.state.bStockData["08. previous close"]} /></td><td>&nbsp;</td>
                                <td><TextField onChange={this.onbVolume.bind(this)} type="number" autoFocus="true" label="Enter Volume" variant="outlined" /></td><td>&nbsp;</td>
                                <td><TextField value={this.state.bStockAmount}type="number" label="Trade Amount" variant="outlined" /></td><td>&nbsp;</td>
                            </tr>
                            <tr><td>&nbsp;</td></tr>
                            <tr>
                                <td><ImgMediaCard title="Volume" value={this.state.bStockData["06. volume"]} /></td><td>&nbsp;</td>
                                <td><ImgMediaCard title="Change" value={this.state.bStockData["09. change"]} /></td><td>&nbsp;</td>
                                <td><ImgMediaCard title="Price" value={this.state.bStockData["05. price"]} /></td><td>&nbsp;</td>
                                <td><ImgMediaCard title="Latest Trading Day" value={this.state.bStockData["07. latest trading day"]} /></td><td>&nbsp;</td>
                                <td><Button size="large" onClick={this.tradeBuy} variant="contained" color="secondary" >Buy the stock</Button></td><td>&nbsp;</td>
                            </tr>
                            
                            <br /><br /><br /><br /><br /><br />
                        </div>}
                    </div> : null)}


                {(this.state.mode == "sell" ?
                    <div style={{
                        backgroundImage: "url(" + sellImg + ")",
                        backgroundPosition: 'center',
                        backgroundSize: 'cover',
                        backgroundRepeat: 'no-repeat'
                    }}>
                        <Button size="large" onClick={this.home} variant="contained" color="primary" >Go Back</Button>

                        {this.state.portfolio == null ? null :
                            <div>
                                <br /><br /><br /><br /><br />
                                <Autocomplete
                                    forcePopupIcon
                                    id="combo-box-name"
                                    options={this.state.portfolio}
                                    getOptionLabel={(option) => option.stock_name}
                                    style={{ width: 800 }}
                                    onChange={this.onTagsChange}
                                    renderInput={(params) =>
                                        <TextField autoFocus="true"
                                            {...params} label="Search stock from your portfolio" variant="outlined" />
                                    }
                                />
                                <br /><br />
                                {(this.state.stock_q == null ? <div><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /></div> : <div>
                                    <TableContainer component={Paper} id="more">
                                        <Table aria-label="simple table">
                                            <TableHead>
                                                <TableRow style={{ background: '#2E3B55', color: '#FFFFFF' }}>
                                                    <TableCell style={{ color: '#FFFFFF' }} >Stock Name</TableCell>
                                                    <TableCell style={{ color: '#FFFFFF' }} align="center">Total Stock Volume</TableCell>
                                                    <TableCell style={{ color: '#FFFFFF' }} align="center">Total Stock Price</TableCell>
                                                    <TableCell style={{ color: '#FFFFFF' }} align="center">Stock Change Info</TableCell>
                                                    <TableCell style={{ color: '#FFFFFF' }} align="center">Stock Unit Price</TableCell>

                                                </TableRow>
                                            </TableHead>
                                            <TableBody>
                                                {this.state.portfolio.map((e) => (

                                                    this.state.stock_q == e.id ?
                                                        <TableRow key={e.stock_name}>
                                                            <TableCell component="th" scope="row">
                                                                {e.stock_name}
                                                            </TableCell>
                                                            <TableCell align="center">{e.stock_volume}</TableCell>
                                                            <TableCell align="center">{e.current_value}</TableCell>
                                                            <TableCell align="center"><font color={e.change_percent.includes("-") ? "red" : "green"}>{e.change_percent} </font></TableCell>
                                                            <TableCell align="center">{e.price_of_stock}</TableCell>

                                                        </TableRow>
                                                        : null
                                                ))}
                                            </TableBody>
                                        </Table>
                                    </TableContainer>
                                    <br />
                                    <TextField type="number" onChange={this.onVolume.bind(this)} autoFocus="true" label="Enter Volume" variant="outlined" /> &nbsp;&nbsp;&nbsp;&nbsp;
                                    <Button size="large" onClick={this.tradeSell.bind(this)} variant="contained" color="secondary" >Sell the stock</Button>
                                    <br /><br />
                                    <TextField type="number" value={this.state.amount} disabled label="Amount" variant="outlined" /><br /><br />
                                    

                                </div>)}
                            </div>}

                    </div> : null)}
            </div>
        )
    }
}
export default Trade