import LabeledInput from "./LabeledInput";
import {controlIds, names, types} from "../utils/formData";

export default function LabelsGroup({labelArray, values, setters}) {
    return (
        <div className='center vertical'>
            {
                labelArray.map((i) => (
                    <LabeledInput
                        controlId={controlIds[i]}
                        name={names[i]}
                        type={types[i]}
                        key={i}
                        value={values[i]}
                        onChange={e => setters[i](e.target.value)}
                    />
                ))
            }
        </div>
    )
}