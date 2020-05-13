import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import Slider from '@material-ui/core/Slider';

const useStyles = makeStyles({
    root: {
        width: 300,
    },
});

function valuetext(value) {
    return `${value}%`;
}

export default function DiscreteSlider() {
    const classes = useStyles();

    return (
        <div style={{
            "margin": "30px 50px 100px 30px",
            "position": "absolute",
            "right": 0,
            "top": 50
        }}>
            <Typography id="discrete-slider" gutterBottom>
                Filter Matching Percentage
            </Typography>
            <Slider
                defaultValue={50}
                getAriaValueText={valuetext}
                aria-labelledby="discrete-slider"
                valueLabelDisplay="auto"
                step={10}
                marks
                min={10}
                max={100}
            />
        </div>
    );
}
