import React from 'react';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
import {makeStyles} from '@material-ui/core/styles';

function Alert(props) {
    return <MuiAlert elevation={6} variant="filled" {...props[0]} />;
}

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        '& > * + *': {
            marginBottom: theme.spacing(20),
        },
    },
}));

export default function CustomizedSnackbars(...props) {
    const classes = useStyles();
    console.log("IS OPEN: ", props[0].autoHideDuration)
    const [open, setOpen] = React.useState(props[0].open);

    const handleClick = () => {
        setOpen(true);
    };

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setOpen(false);
    };

    return (
        <div className={classes.root}>
            <Snackbar anchorOrigin={{vertical: 'top', horizontal: 'center'}}
                      open={props[0].open}
                      onClose={handleClose}>
                <Alert {...props}/>
            </Snackbar>
        </div>
    );
}
