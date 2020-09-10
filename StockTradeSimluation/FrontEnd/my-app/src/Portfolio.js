import React, { useRef } from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import ReactApexChart from 'react-apexcharts';
import PORT from './assets/port.png';
import CustomizedDialogs from './popup'
import { Button } from '@material-ui/core';

class Portfolio extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            myRef : null,
            userID: "sample",
            portfolio: null,
            series: null,
            options: {
                chart: {
                    type: 'donut',
                    toolbar: {
                        show: true,
                    }
                },
                theme: {
                    mode: 'light', 
                    palette: 'palette3', 
                    monochrome: {
                        enabled: false,
                        color: '#255aee',
                        shadeTo: 'light',
                        shadeIntensity: 0.65
                    },
                },
                labels: ['Team A', 'Team B', 'Team C',],
                responsive: [{
                    
                    options: {
                        chart: {
                            width: 100
                        },
                        legend: {
                            position: 'bottom',
                            offsetX: -400,
                        }
                    }
                }],
                
            },
        };

    }

    componentDidMount() {
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

    render() {
        var row = this.state.portfolio
        var graphData = []
        var label =[]
        var updatedOptions = this.state.options
        if(this.state.portfolio !=null){
            this.state.portfolio.map(element => {
                graphData.push(parseInt(element.stock_volume))
                label.push(element.stock_name)
            });
        updatedOptions.labels = label
        }

        return (
            <div style={{backgroundImage: "url(" + PORT + ")" ,
            backgroundPosition: 'center',
            backgroundSize: 'cover',
            backgroundRepeat: 'no-repeat'}}>
                
               <center> <h1>MY PORTFOLIO</h1></center>
                {(this.state.portfolio == null) ? null :
                <div id="chart">
                    <a style={{ textDecoration: 'none', scrollBehavior: 'smooth'}} href="#more"><Button variant="contained" color="primary">Let's View my Portfolio in Detail</Button></a>
                    <ReactApexChart options={updatedOptions} series={graphData} type="donut" height="450" />
                    
                </div>
                }
               
                {
                    (this.state.portfolio == null) ? null :
                        <TableContainer component={Paper} id ="more">
                            <Table aria-label="simple table">
                                <TableHead>
                                    <TableRow style={{ background: '#2E3B55', color: '#FFFFFF' }}>
                                        <TableCell style={{ color: '#FFFFFF' }} >Stock Name</TableCell>
                                        <TableCell style={{ color: '#FFFFFF' }} align="center">Total Stock Volume</TableCell>
                                        <TableCell style={{ color: '#FFFFFF' }} align="center">Total Stock Price</TableCell>
                                        <TableCell style={{ color: '#FFFFFF' }} align="center">Stock Change Info</TableCell>
                                        <TableCell style={{ color: '#FFFFFF' }} align="center">Stock Unit Price</TableCell>
                                        <TableCell style={{ color: '#FFFFFF' }} align="center">View Insight</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {row.map((e) => (
                                        <TableRow key={e.stock_name}>
                                            <TableCell component="th" scope="row">
                                                {e.stock_name}
                                            </TableCell>
                                            <TableCell align="center">{e.stock_volume}</TableCell>
                                            <TableCell align="center">{e.current_value}</TableCell>
                                            <TableCell align="center"><font color={e.change_percent.includes("-")?"red":"green"}>{e.change_percent} </font></TableCell>
                                            <TableCell align="center">{e.price_of_stock}</TableCell>
                                            <TableCell align="center"><CustomizedDialogs/></TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                }
            </div>
        )
    }
}
export default Portfolio;