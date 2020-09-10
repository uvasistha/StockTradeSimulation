import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    root: {
        maxWidth: 345,
    },
});

export default function ImgMediaCard (props) {
    const classes = useStyles();

    var defbg = '#2E3B55'
    var defcl = '#FFFFFF'
     if(props.title =="High")  defbg = '#415231'
     if(props.title =="Low") defbg = '#6f2519'
     if(props.title =="Price") defbg = '#5a4129'
    //  if(props.title =="Open") defbg = '#40354e'
    //  if(props.title =="Volume") defbg = '#0d6769'
     if(props.title =="Change") {
        (props.value.includes("-"))? defbg= '#58111a': defbg ='#005400'
    }

    return (
        <Card className={classes.root}  style={{ background: defbg, color: defcl }}>
            <CardActionArea>
                <CardContent>
                    <Typography gutterBottom variant="h5" component="h2">
                        {props.title}<br/> {props.value}
                </Typography>
                </CardContent>
            </CardActionArea>
        </Card>
    );
}
