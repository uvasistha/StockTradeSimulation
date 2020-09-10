import React from 'react';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import ImgMediaCard from './Cards';
import symbolList from './symbolList.json'
import { Typography, ThemeProvider, createMuiTheme } from '@material-ui/core';
import NYSE from './assets/NYSE.png';
import NASDAQ from './assets/NASDAQ.png';
import ASX from './assets/ASX.png';
import DASH from './assets/Dash.jpg';

class Dashboard extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            exploreList: null,
            tags:null,
            stock_q:null,
            stockData :null,
        };
        this.onStockChoose = this.onStockChoose.bind(this)
        this.onTagsChange = this.onTagsChange.bind(this);
    }

    onStockChoose(){
        //get real time value 
        var stockid = this.state.stock_q
        if(stockid==null)return
        var url = 'http://localhost:8982/dataService/get/stock/'+stockid
        fetch(url, {
            method: 'GET', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(response => response.json())
            .then(data => {
                this.setState({ stockData: data })
                console.log(data);
            })
            .catch((error) => {
                this.setState({ stockData : null })
                console.error('Error:', error);
            });
    }

    onTagsChange = (event, values) => {
        this.setState({
          tags: values
        }, () => (
          (this.state.tags!=null)?this.setState({stock_q:this.state.tags.id},()=>(this.onStockChoose())):this.setState({stock_q:null})
        ));
      }

    componentDidMount() {
        this.setState({ exploreList: symbolList }
            )
            /**(e)=>(this.state.exploreList.map(
                (d)=>(d.name = d.name + " ( "+d.id+" )")
            )) */
    }

    render() {
        return (
            <div style={{backgroundImage: "url(" + DASH + ")" ,
            backgroundPosition: 'center',
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat'}}>
                {this.state.exploreList==null?null:
                <div>
                  <br/>
                  
                  <center>
                    <img height="100" width="100"  src={NYSE} alt="Logo"  />&nbsp;&nbsp;
                    <img height="100" width="200"  src={NASDAQ} alt="Logo" /> &nbsp;&nbsp;
                    <img height="100" width="100"  src={ASX} alt="Logo" />
                    </center>
                  
                  <br/><br/><br/>
                <Autocomplete
                    forcePopupIcon
                    id="combo-box-name"
                    options={this.state.exploreList}
                    getOptionLabel={(option) => option.name}
                    style={{ width: 800 }}
                    onChange={this.onTagsChange}
                    renderInput={(params) =>                    
                    <TextField autoFocus="true"
                     {...params} label="Click here to see what market has to offer : Search Stock Here (ex :  Google)" variant="outlined" />
                    }
                />
                 </div>
                }
                <br/>

                {(this.state.stock_q!=null && this.state.stockData!=null)
                ?
                <div>
                     <Typography gutterBottom variant="h5" component="h2">Lets understand {this.state.stock_q} in real time </Typography>
                <td>&nbsp;</td><td>&nbsp;</td>
                <td>
                <tr>
                <td><ImgMediaCard title="Open" value={this.state.stockData["02. open"]} /></td><td>&nbsp;</td>
                <td><ImgMediaCard title="High" value={this.state.stockData["03. high"]}/></td><td>&nbsp;</td>
                <td><ImgMediaCard title="Low" value={this.state.stockData["04. low"]}/></td><td>&nbsp;</td>
                <td><ImgMediaCard title="Previous Close" value={this.state.stockData["08. previous close"]}/></td><td>&nbsp;</td>
                </tr>
                <tr><td>&nbsp;</td></tr>
                <tr>
                <td><ImgMediaCard title="Volume" value={this.state.stockData["06. volume"]}/></td><td>&nbsp;</td>
                <td><ImgMediaCard title="Change" value={this.state.stockData["09. change"]}/></td><td>&nbsp;</td>
                <td><ImgMediaCard title="Price" value={this.state.stockData["05. price"]}/></td><td>&nbsp;</td>
                <td><ImgMediaCard title="Latest Trading Day" value={this.state.stockData["07. latest trading day"]}/></td><td>&nbsp;</td>
                </tr>
                </td>
                <br/><br/>
                </div>
                :<div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/></div>}
                
            </div>
        )
    }
}
export default Dashboard

